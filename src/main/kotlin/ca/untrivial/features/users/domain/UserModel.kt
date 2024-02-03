package ca.untrivial.features.users.domain

import kotlinx.serialization.Serializable
import java.util.*

data class User(val username: String, val email: String, val password: String, val displayName: String?)

//TODO: figure out how to make displayName optional
@Serializable
data class UserDTO(val username: String, val email: String, val displayName: String?)

fun User.toDTO(): UserDTO = UserDTO(username, email, displayName)

fun UserDTO.toModel(pw: String): User = User(username, email, pw, displayName)
