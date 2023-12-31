package ca.untrivial.routes

import ca.untrivial.features.games.GameService
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
            println("FOUND ID: " + id)
            val game = gameService.getGame(id)
            call.respond(FreeMarkerContent("game.ftl", model = mapOf("game" to game)))
        }
    }
}