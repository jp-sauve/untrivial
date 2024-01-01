package ca.untrivial

import ca.untrivial.dao.DatabaseSingleton
import ca.untrivial.plugins.*
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}
fun Application.configureKoin() {
    install(Koin)
}
fun Application.module() {
    DatabaseSingleton.init(environment.config)
    configureSerialization()
    configureRouting()
    configureTemplating()
}
