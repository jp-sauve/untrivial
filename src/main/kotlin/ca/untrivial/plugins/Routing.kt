package ca.untrivial.plugins

import ca.untrivial.routes.articleRouting
import ca.untrivial.routes.userRouting
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        staticResources("/static", "files")
        userRouting()
        articleRouting()
    }
}
