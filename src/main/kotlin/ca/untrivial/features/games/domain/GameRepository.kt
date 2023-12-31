package ca.untrivial.features.games.domain

import ca.untrivial.dao.DatabaseSingleton.dbQuery
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import java.util.*


object Games : UUIDTable() {
    val name = varchar("name", 50)
    val variant = varchar("variant", 150)
}

class GameRepository {
    suspend fun getAllGames(): List<GameDTO> = dbQuery {
        Games.selectAll().map {
            GameDTO(
                id = it[Games.id].toString(),
                name = it[Games.name],
                variant = it[Games.variant]
            )
        }
    }

suspend fun game(id: UUID): GameDTO? = dbQuery {
    Games.select { Games.id eq id }.map {
        GameDTO(
            id = it[Games.id].toString(),
            name = it[Games.name],
            variant = it[Games.variant]
        )
    }.firstOrNull()

}
//    private fun toGame(row: ResultRow) = Game(
//        name = row[Games.name],
//        variant = row[Games.variant]
//    )
}