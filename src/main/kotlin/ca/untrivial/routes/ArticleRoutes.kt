package ca.untrivial.routes

import ca.untrivial.dao.DAOFacade
import ca.untrivial.models.Article
import ca.untrivial.models.Articles
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.http.*
import io.ktor.server.freemarker.*
import io.ktor.server.util.*

fun Route.articleRouting(dao: DAOFacade) {
    route("/article") {
        get {
            // show all articles
            call.respond(FreeMarkerContent("index.ftl", mapOf("articles" to dao.allArticles())))
        }
        get("new") {
            // form to add new article
            call.respond(FreeMarkerContent("new.ftl", model=null))
        }
        post {
            // save an article
            val formParameters = call.receiveParameters()
            val title = formParameters.getOrFail("title")
            val body = formParameters.getOrFail("body")
            val newEntry = dao.addNewArticle(title, body)
            call.respondRedirect("/article/${newEntry?.id}")
        }
        get("{id}") {
            // show a specific article by ID
            val id = call.parameters.getOrFail<Int>("id").toInt()
            val article = dao.article(id)
            call.respond(FreeMarkerContent("article.ftl", model = mapOf("article" to article)))
        }
        get("{id}/edit") {
            // form to edit article
            val id = call.parameters.getOrFail<Int>("id").toInt()
            val article = dao.article(id)
            call.respond(FreeMarkerContent("edit.ftl", model = mapOf("article" to article)))
        }
        post("{id}") {
            // update article
            val id = call.parameters.getOrFail<Int>("id").toInt()

            val formParameters = call.receiveParameters()
            when (formParameters.getOrFail("_action")) {
                "update" -> {
                    val title = formParameters.getOrFail("title")
                    val body = formParameters.getOrFail("body")
                    dao.editArticle(id, title, body)
                    call.respondRedirect("/article/${id}")
                }
                "delete" -> {
                    dao.deleteArticle(id)
                    call.respondRedirect("/article")
                }
            }

        }
    }
}