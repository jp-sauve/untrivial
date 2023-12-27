package ca.untrivial.plugins

import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import freemarker.cache.*
import freemarker.core.HTMLOutputFormat

fun Application.configureTemplating() {
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
        outputFormat = HTMLOutputFormat.INSTANCE
    }
}