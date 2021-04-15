package com.example.pythian_games.viewModels

import androidx.lifecycle.ViewModel
import com.example.pythian_games.data.CardToQuestion
import com.example.pythian_games.data.Question
import com.example.pythian_games.repositories.GameRepository

class CardViewModel : ViewModel() {
    var repository = GameRepository.instance

    fun getCurrentQuestion(): CardToQuestion {
        return repository.currentQuestion.value!!
    }

    fun setCurrentQuestion(cardNum: Int) {
        repository.setCurrentQuestion(cardNum)
    }

    fun handleAnswer(answeredNum: Int) {

    }

}