package ca.untrivial.features.games.domain

import ca.untrivial.dao.DatabaseSingleton.dbQuery
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.*
import java.util.*


object Games : UUIDTable() {
    val name = varchar("name", 50)
    val variant = varchar("variant", 150)
}

class GameRepository {
    suspend fun allGames(): List<GameDTO> = dbQuery {
        Games.selectAll().map {
            GameDTO(
                id = it[Games.id].toString(), name = it[Games.name], variant = it[Games.variant]
            )
        }
    }

    suspend fun game(id: UUID): GameDTO? = dbQuery {
        Games.select { Games.id eq id }.map {
            GameDTO(
                id = it[Games.id].toString(), name = it[Games.name], variant = it[Games.variant]
            )
        }.firstOrNull()
    }
    suspend fun add(dto: GameDTO): UUID = dbQuery {
        val gameId = Games.insertAndGetId {
            it[Games.name] = dto.name
            it[Games.variant] = dto.variant
        }
        gameId.value
    }
}