package ca.untrivial.plugins

import ca.untrivial.dao.DAOFacade
import ca.untrivial.dao.DAOFacadeCacheImpl
import ca.untrivial.dao.DAOFacadeImpl

import ca.untrivial.features.games.GameService
import ca.untrivial.features.games.domain.GameRepository
import ca.untrivial.routes.api.gameApiRouting
import ca.untrivial.routes.articleRouting
import ca.untrivial.routes.gameRouting
import ca.untrivial.routes.userRouting
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.transactions.transaction
import java.io.File

fun Application.configureRouting() {
    // TODO() make a new facade and cache games as well. One facade for each bit?
    // I'm not sure of the best pattern here.
    val dao: DAOFacade = DAOFacadeCacheImpl(
        DAOFacadeImpl(),
        File(environment.config.property("storage.ehcacheFilePath").getString())
    ).apply {
        runBlocking {
            if (allArticles().isEmpty()) {
                addNewArticle("First Post!", "Sucker. You will always be first loser to me.")
                addNewArticle("Second Post!", "Sucker. You will always be second loser to me.")
            }

        }
    }
    val gameRepository = GameRepository()
    val gameService = GameService(gameRepository)
        .apply {
            runBlocking {
                if (getAllGames().isEmpty()) {
                    addNewGame("Otter brand Trivia", "Tummy snacks edition")
                    addNewGame("Beaver brand Trivia", "Wood snacks edition")
                    addNewGame("Goat brand Trivia", "Any snacks edition")
                    addNewGame("Dragon brand Trivia", "Gold snacks edition")
                    addNewGame("Fish brand Trivia", "Wet snacks edition")
                }
            }
        }
    routing {
        staticResources("/static", "files")
        userRouting()
        articleRouting(dao)
        gameRouting(gameService)
        gameApiRouting(gameService)
    }
}
