package ca.untrivial.models

import kotlinx.serialization.Serializable

@Serializable
data class Game(val id: String, val rounds: List<Round>)

@Serializable
data class Round(val id: String, val questions: List<Question>)

@Serializable
data class Question(val id: String, val prompt: String,val correctChoice: Choice, val incorrectChoices: List<Choice>)

@Serializable
data class Choice(val id: String, val isCorrect: Boolean, val choiceText: String)