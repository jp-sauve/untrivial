package ca.untrivial.features.games.domain
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

data class Game(val id: UUID, val name: String, val variant: String)

data class GameDTO(val id: String, val name: String, val variant: String)

fun Game.toDTO(): GameDTO = GameDTO(id.toString() , name, variant)

fun GameDTO.toModel(): Game = Game(UUID.fromString(id), name, variant)