package ca.untrivial.features.users.domain

import kotlinx.serialization.Serializable
import java.util.*
@Serializable
data class User(val username: String, val email: String, val password: String)


data class UserDTO(val username: String, val email: String)

fun User.toDTO(): UserDTO = UserDTO(username, email)

fun UserDTO.toModel(pw: String): User = User(username, email, pw)
