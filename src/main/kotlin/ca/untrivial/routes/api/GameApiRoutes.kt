package ca.untrivial.routes.api

import ca.untrivial.features.games.GameService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.cors.routing.*
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
        }
    }
}