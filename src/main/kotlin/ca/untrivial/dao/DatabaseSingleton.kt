package ca.untrivial.dao

import ca.untrivial.features.games.domain.Games
import ca.untrivial.features.users.domain.Users
import ca.untrivial.models.*
import java.io.*
import io.ktor.server.config.*
import kotlinx.coroutines.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.*
import org.jetbrains.exposed.sql.transactions.experimental.*
import com.zaxxer.hikari.*

object DatabaseSingleton {
        private fun createHikariDataSource(
        url: String,
        driver: String
    ) = HikariDataSource(HikariConfig().apply {
        driverClassName = driver
        jdbcUrl = url
        maximumPoolSize = 3
        isAutoCommit = false
        transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        validate()
    })
    fun init(config: ApplicationConfig) {
        val driverClassName = config.property("storage.driverClassName").getString()
        val jdbcURL = (config.property("storage.jdbcURL").getString() +
                config.property("storage.dbFilePath").getString().let {
                    File(it).canonicalFile.absolutePath
                }) ?: ""
        val database = Database.connect(createHikariDataSource(url = jdbcURL, driver = driverClassName))

        transaction(database) {
            SchemaUtils.create(Articles)
            SchemaUtils.create(Games)
            SchemaUtils.create(Users)
        }
    }
    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) {
            // addLogger(StdOutSqlLogger)
            block()
        }
}
