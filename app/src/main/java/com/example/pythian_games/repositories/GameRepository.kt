package com.example.pythian_games.repositories

import androidx.lifecycle.MutableLiveData
import com.example.pythian_games.data.CardToQuestion
import com.example.pythian_games.data.CleaningQuestion
import com.example.pythian_games.data.Question
import com.example.pythian_games.firebaseDB.FirebaseDB

class GameRepository {
    companion object {
        val instance : GameRepository by lazy { holder.Instance }
    }

    private object holder {
        val Instance = GameRepository()
    }

    var firebaseDB = FirebaseDB()

    var questionPool: MutableLiveData<ArrayList<CardToQuestion>> = MutableLiveData()
    var cleaningQuestions: MutableLiveData<ArrayList<CleaningQuestion>> = MutableLiveData()
    var currentQuestion: MutableLiveData<CardToQuestion> = MutableLiveData()

    //TODO временный хардкод
    init {
        /*firebaseDB.addQuestion(
            Question(1, "Вопрос номер один. Дайте ответ на него.", "Ответ 1 на 1 вопрос",
                "Ответ 2 на 1 вопрос", "Ответ 3 на 1 вопрос", "Ответ 4 на 1 вопрос", 3, 1))
        firebaseDB.addQuestion(
            Question(2, "Вопрос номер два. Дайте ответ на него.", "Ответ 1 на 2 вопрос",
                "Ответ 2 на 2 вопрос", "Ответ 3 на 2 вопрос", "Ответ 4 на 2 вопрос", 1, 2))
        firebaseDB.addQuestion(
            Question(3, "Вопрос номер три. Дайте ответ на него.", "Ответ 1 на 3 вопрос",
                "Ответ 2 на 3 вопрос", "Ответ 3 на 3 вопрос", "Ответ 4 на 3 вопрос", 2, 3))
        firebaseDB.addQuestion(
            Question(4, "Вопрос номер четыре. Дайте ответ на него.", "Ответ 1 на 4 вопрос",
                "Ответ 2 на 4 вопрос", "Ответ 3 на 4 вопрос", "Ответ 4 на 4 вопрос", 4, 4))
        */

        currentQuestion.postValue(CardToQuestion())

        firebaseDB.getAllQuestions {
            var list: ArrayList<CardToQuestion> = ArrayList()
            list.add(CardToQuestion(123, it[0]))
            list.add(CardToQuestion(76, it[1]))
            list.add(CardToQuestion(423, it[2]))
            list.add(CardToQuestion(50, it[3]))

            questionPool.postValue(list)
        }
    }

    fun setCurrentQuestion(cardNum: Int) {
        for (item in questionPool.value!!) {
            if (item.cardNumber == cardNum) {
                currentQuestion.postValue(item)
            }
        }
    }

}