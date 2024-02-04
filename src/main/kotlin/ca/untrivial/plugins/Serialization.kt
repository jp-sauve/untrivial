package ca.untrivial.plugins

import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        jackson()
    }
}
