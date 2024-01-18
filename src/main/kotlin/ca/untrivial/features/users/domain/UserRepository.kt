package ca.untrivial.features.users.domain

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object Users: IntIdTable() {
    val username: Column<String> = varchar("username", 50).uniqueIndex()
    val password = varchar("password", 64)
    val email = varchar("email", 320).uniqueIndex()
}
class UserRepository {
}