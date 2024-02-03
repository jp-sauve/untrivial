package ca.untrivial.routes.api

import ca.untrivial.features.games.GameService
import ca.untrivial.features.games.domain.Game
import ca.untrivial.features.games.domain.GameDTO
import ca.untrivial.features.games.domain.toDTO
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.server.response.*

fun Route.gameApiRouting(gameService: GameService) {
    route("/api") {
        install(CORS) {
            anyHost()
            allowHost("0.0.0.0:8080")
            allowHeader(HttpHeaders.ContentType)
        }
        route("/game") {
            get {
                call.respond(gameService.getAllGames())
            }
            post {
                val game = call.receive<Game>()
                val gameUUID = gameService.addNewGame(game.name, game.variant);
                call.respond<GameDTO>(game.toDTO())
            }
        }
    }
}