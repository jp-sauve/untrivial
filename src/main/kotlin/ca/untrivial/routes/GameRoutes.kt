package ca.untrivial.routes

import ca.untrivial.features.games.GameService
import io.ktor.server.freemarker.*
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
fun Route.gameRouting(gameService: GameService) {
    route("/game") {
        get {
            call.respond(FreeMarkerContent("games.ftl", mapOf("games" to gameService.getAllGames())))
        }
    }
}