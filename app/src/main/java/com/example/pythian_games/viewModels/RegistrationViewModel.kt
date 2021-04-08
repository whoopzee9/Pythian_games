package com.example.pythian_games.viewModels

import androidx.lifecycle.ViewModel
import com.example.pythian_games.repositories.RegistrationRepository

class RegistrationViewModel : ViewModel() {
    var repository = RegistrationRepository.instance


}