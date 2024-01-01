package ca.untrivial.routes

import ca.untrivial.features.games.GameService
import io.ktor.http.*
import io.ktor.server.freemarker.*
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.util.*

fun Route.gameRouting(gameService: GameService) {
    route("/game") {
        get {
            call.respond(FreeMarkerContent("games.ftl", mapOf("games" to gameService.getAllGames())))
        }

        get("{id}") {
            val id = call.parameters.getOrFail<String>("id")
            val game = gameService.getGame(id)
            call.respond(FreeMarkerContent("game.ftl", model = mapOf("game" to game)))
        }
        get("delete/{id}") {
            val id = call.parameters.getOrFail<String>("id")
            val doneDeal = gameService.deleteGame(id)
            if (doneDeal) {
                call.respondRedirect("/game")
            } else {
                call.respondText(text = "Nothing to delete for {id}", status = HttpStatusCode.NotFound)
            }
        }
    }
}