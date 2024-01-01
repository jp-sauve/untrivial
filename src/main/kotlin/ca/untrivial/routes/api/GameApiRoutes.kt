package ca.untrivial.routes.api

import ca.untrivial.features.games.GameService
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.response.*

fun Route.gameApiRouting(gameService: GameService) {
    route("/api") {
        route("/game") {
            get {
                call.respond(gameService.getAllGames())
            }
        }
    }
}