package com.example.pythian_games.viewModels

import androidx.lifecycle.ViewModel
import com.example.pythian_games.repositories.GameRepository

class ScanViewModel : ViewModel() {
    var repository = GameRepository.instance
    // TODO: Implement the ViewModel
}