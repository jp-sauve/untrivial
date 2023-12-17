package ca.untrivial.routes

import ca.untrivial.models.User
import ca.untrivial.models.userStorage
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

fun Route.userRouting() {
    route("/user") {
        get {
            if (userStorage.isNotEmpty()) {
                call.respond(userStorage)
            } else {
                call.respondText("Nothing to see", status = HttpStatusCode.NoContent)
            }
        }
        get("{id?}") {
            val id = call.parameters["id"] ?: return@get call.respondText("Missing ID!", status = HttpStatusCode.BadRequest)
            val customer = userStorage.find { user -> user.id == id } ?: return@get call.respondText("No user with id $id", status = HttpStatusCode.NotFound)
            call.respond(customer)
        }
        post {
            val user = call.receive<User>()
            userStorage.add(user)
            println(user.firstName)
            call.respondText("New user stored", status = HttpStatusCode.Created)
        }
        delete("{id?}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            if (userStorage.removeIf { it.id == id}) {
                call.respondText("User id $id removed successfully", status = HttpStatusCode.Accepted)
            } else {
                call.respondText("User not found", status = HttpStatusCode.NotFound)
            }
        }
    }
}