package ca.untrivial.features.games.domain
import org.jetbrains.exposed.dao.id.UUIDTable
import java.io.Serializable

data class Game(val name: String, val variant: String): Serializable


