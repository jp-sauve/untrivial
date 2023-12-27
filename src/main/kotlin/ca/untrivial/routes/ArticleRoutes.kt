package ca.untrivial.routes

import ca.untrivial.models.Article
import ca.untrivial.models.articles
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.http.*
import io.ktor.server.freemarker.*
import io.ktor.server.util.*

fun Route.articleRouting() {
    route("/article") {
        get {
            // show all articles
            call.respond(FreeMarkerContent("index.ftl", mapOf("articles" to articles)))
        }
        get("new") {
            // form to add new article
            call.respond(FreeMarkerContent("new.ftl", model=null))
        }
        post {
            // save an article
            val formParameters = call.receiveParameters()
            val title = formParameters.getOrFail("title")
            println("title: $title")
            val body = formParameters.getOrFail("body")
            println("body: $body")
            val newEntry = Article.newEntry(title, body)
            articles.add(newEntry)
            call.respondRedirect("/article/${newEntry.id}")
        }
        get("{id}") {
            // show a specific article by ID
            val id = call.parameters.getOrFail<Int>("id").toInt()
            val article = articles.find { it.id == id }
            call.respond(FreeMarkerContent("article.ftl", model = mapOf("article" to article)))
        }
        get("{id}/edit") {
            // form to edit article
        }
        post("{id}") {
            // update article
        }
    }
}