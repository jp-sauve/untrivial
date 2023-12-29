package ca.untrivial.models

import org.jetbrains.exposed.sql.*
import java.io.Serializable


data class Game(val id: String, val rounds: List<Round>): Serializable

data class Round(val id: String, val questions: List<Question>): Serializable

data class Question(val id: String, val prompt: String,val correctChoice: Choice, val incorrectChoices: List<Choice>): Serializable

data class Choice(val id: String, val isCorrect: Boolean, val choiceText: String): Serializable

object Games: Table() {
    val id = integer("id").autoIncrement()
    val rounds = varchar("rounds", 128)
}