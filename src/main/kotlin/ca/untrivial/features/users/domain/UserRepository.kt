package ca.untrivial.features.users.domain

import ca.untrivial.dao.DatabaseSingleton.dbQuery
import ca.untrivial.features.games.domain.Games
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.mindrot.jbcrypt.BCrypt
import java.util.*
object Users: IntIdTable() {
    val username: Column<String> = varchar("username", 50).uniqueIndex()
    val password = varchar("password", 64)
    val email = varchar("email", 320).uniqueIndex()
    val displayName = varchar("displayName", 128)
}
class UserRepository {
    suspend fun allUsers(): List<UserDTO> = dbQuery {
        Users.selectAll().map {
            UserDTO(username = it[Users.username], email = it[Users.email])
        }
    }
    suspend fun user(id: Int): UserDTO? = dbQuery {
        Users.select { Users.id eq id }.singleOrNull()?.let {
            UserDTO(username = it[Users.username], email = it[Users.email])
        }
    }
    suspend fun add(username: String, password: String, email: String): Int = dbQuery {
        val userId = Users.insertAndGetId {
            it[Users.username] = username
            it[Users.email] = email
            it[Users.password] = BCrypt.hashpw(password, BCrypt.gensalt())
        }
        userId.value
    }
    suspend fun edit(id: Int, username: String, password: String, email: String): Boolean = dbQuery {
        val updatedRows = Users.update({Users.id eq id}) {
            it[Users.username] = username
            it[Users.email] = email
            it[Users.password] = BCrypt.hashpw(password, BCrypt.gensalt())
        }
        updatedRows > 0
    }
    suspend fun delete(id: Int): Boolean = dbQuery {
        val updatedRows = Users.deleteWhere {
            this.id eq id
        }
        updatedRows > 0

    }
}