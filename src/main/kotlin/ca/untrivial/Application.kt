package ca.untrivial

import ca.untrivial.dao.DatabaseSingleton
import ca.untrivial.plugins.*
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}
fun Application.configureKoin() {
    install(Koin) {
        slf4jLogger()
    }
}
fun Application.module() {
    configureKoin()
    DatabaseSingleton.init(environment.config)
    configureSerialization()
    configureRouting()
    configureTemplating()
}
