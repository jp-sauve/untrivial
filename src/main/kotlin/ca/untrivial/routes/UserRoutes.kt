package ca.untrivial.routes

import ca.untrivial.features.users.domain.UserDTO
import ca.untrivial.features.users.domain.UserService
import ca.untrivial.features.users.domain.User
import ca.untrivial.features.users.domain.toDTO
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

fun Route.userRouting(userService: UserService) {
    val truth: Boolean = true
    route("/user") {
        get {
            if (truth) {
                call.respond(userService.getAllUsers())
            } else {
                call.respondText("Nothing to see", status = HttpStatusCode.NoContent)
            }
        }
        get("{id?}") {
            val id = call.parameters["id"]?.toInt() ?: return@get call.respondText("Missing ID!", status = HttpStatusCode.BadRequest)
//            val customer = userStorage.find { user -> user.id == id } ?: return@get call.respondText("No user with id $id", status = HttpStatusCode.NotFound)
            val customer = userService.getUser(id)
            if (customer != null) {
                call.respond<UserDTO>(customer)
            } else {
                call.respondText(text = "Nothing to see for {}", contentType = ContentType.Text.Plain)
            }

        }
        post {
            val user = call.receive<User>()
            userService.addUser(user.username, user.email, user.password)
            //call.respondText("New user stored", status = HttpStatusCode.Created)
            call.respond<UserDTO>(user.toDTO())
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