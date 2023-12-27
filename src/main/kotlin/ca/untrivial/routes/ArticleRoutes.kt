package ca.untrivial.routes

import ca.untrivial.models.articles
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.http.*
import io.ktor.server.freemarker.*

fun Route.articleRouting() {
    route("/article") {
        get {
            // show all articles
            call.respond(FreeMarkerContent("index.ftl", mapOf("articles" to articles)))
        }
        get("new") {
            // form to add new article
        }
        post {
            // save an article
        }
        get("{id}") {
            // show a specific article by ID
        }
        get("{id}/edit") {
            // form to edit article
        }
        post("{id}") {
            // update article
        }
    }
}