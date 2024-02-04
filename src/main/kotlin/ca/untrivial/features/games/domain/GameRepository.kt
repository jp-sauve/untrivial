package ca.untrivial.features.games.domain

import ca.untrivial.dao.DatabaseSingleton.dbQuery
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Table.Dual.entityId
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

//    suspend fun game(id: UUID): GameDTO? = dbQuery {
//        Games.select { Games.id eq id }.map {
//            GameDTO(
//                id = it[Games.id].toString(), name = it[Games.name], variant = it[Games.variant]
//            )
//        }.singleOrNull()
//    }

    suspend fun game(id: UUID): GameDTO? = dbQuery {
        Games.select { Games.id eq id }.singleOrNull()?.let {
            GameDTO(
                id = it[Games.id].toString(), name = it[Games.name], variant = it[Games.variant]
            )
        }
    }
    suspend fun add(name: String, variant: String): Game = dbQuery {
        var gameId: UUID
        Games.insertAndGetId {
            gameId = this.id
            it[Games.name] = name
            it[Games.variant] = variant
        }
        Game(id = gameId,name,variant)
    }

    suspend fun edit(dto: GameDTO): Boolean = dbQuery {
        val updatedRows = Games.update({Games.id eq UUID.fromString(dto.id)}) {
            it[Games.name] = name
            it[Games.variant] = variant
        }
        updatedRows > 0
    }
    suspend fun delete(id: UUID):Boolean = dbQuery {
        val updatedRows = Games.deleteWhere {
            this.id eq id
        }
        updatedRows > 0

    }
}