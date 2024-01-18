package ca.untrivial.features.users.domain

import kotlinx.serialization.Serializable
import java.util.*

data class User(val username: String, val password: String, val email: String)

@Serializable
data class UserDTO(val username: String, val password: String, val email: String)

fun User.toDTO(): UserDTO = UserDTO(username, password, email)

fun UserDTO.toModel(): User = User(username, password, email)
