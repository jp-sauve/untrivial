package ca.untrivial.features.users.domain

import ca.untrivial.dao.DatabaseSingleton.dbQuery
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.util.*
object Users: IntIdTable() {
    val username: Column<String> = varchar("username", 50).uniqueIndex()
    val password = varchar("password", 64)
    val email = varchar("email", 320).uniqueIndex()
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

}