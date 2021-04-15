package com.example.pythian_games.firebaseDB

import com.example.pythian_games.data.CleaningQuestion
import com.example.pythian_games.data.Question
import com.example.pythian_games.data.User

interface ExtensionsCRUD {
    fun createRoom(name: String, secretCode: String)
    fun joinRoom(secretCode: String)
    fun deleteRoom(name: String)

    fun addQuestion(question: Question)
    fun getAllQuestions(callBack: (MutableList<Question>) -> Unit)

    fun createUser(userData: User)

    fun addQuestionNumberToUsersList(number: Int)

    fun addCleaningQuestion(question: CleaningQuestion)
    fun getCleaningQuestions(callBack: (MutableList<CleaningQuestion>) -> Unit)
    fun deleteAnsweredQuestion(cardNumber: Int)
}