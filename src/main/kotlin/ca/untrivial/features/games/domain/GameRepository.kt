package ca.untrivial.features.games.domain

import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

data class gameDTO(
    val id: UUID,
    val name: String,
    val variant: String,
    )

object Games : UUIDTable() {
    val name = varchar("name", 50)
    val variant = varchar("variant", 150)
}

class GameRepository {
    fun getAllGames(): List<Game> =
}