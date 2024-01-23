package ca.untrivial.features.users.domain

class UserService(private val userRepo: UserRepository) {
    suspend fun getAllUsers(): List<UserDTO> = userRepo.allUsers()

    suspend fun getUser(id: Int): UserDTO? = userRepo.user(id)

    suspend fun addUser(username: String, email: String, password: String): Int {
        return userRepo.add(username, password, email)
    }
    suspend fun deleteUser(id: Int): Boolean = userRepo.delete(id)
}