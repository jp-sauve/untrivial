package ca.untrivial.plugins

import ca.untrivial.dao.DAOFacade
import ca.untrivial.dao.DAOFacadeCacheImpl
import ca.untrivial.dao.DAOFacadeImpl
import ca.untrivial.routes.articleRouting
import ca.untrivial.routes.userRouting
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.runBlocking
import java.io.File

fun Application.configureRouting() {
    val dao: DAOFacade = DAOFacadeCacheImpl(
        DAOFacadeImpl(),
        File(environment.config.property("storage.ehcacheFilePath").getString())
    ).apply {
        runBlocking {
            if (allArticles().isEmpty()) {
                addNewArticle("First Post!", "Sucker. You will always be first loser to me.")
            }
        }
    }
    routing {
        staticResources("/static", "files")
        userRouting()
        articleRouting(dao)
    }
}
