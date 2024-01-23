package ca.untrivial.features.games

import ca.untrivial.features.games.domain.Game
import ca.untrivial.features.games.domain.GameDTO
import ca.untrivial.features.games.domain.GameRepository
import org.jetbrains.exposed.dao.Entity
import java.util.*

class GameService(private val gameRepo: GameRepository) {
    suspend fun getAllGames(): List<GameDTO> = gameRepo.allGames()

    suspend fun getGame(id: String): GameDTO? = gameRepo.game(UUID.fromString(id))

    suspend fun addNewGame(name: String, variant: String): UUID = gameRepo.add(name, variant)

    suspend fun deleteGame(id: String): Boolean = gameRepo.delete(UUID.fromString(id))
}