package com.example.pythian_games.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.pythian_games.repositories.RegistrationRepository

class RegistrationViewModel : ViewModel() {
    var repository = RegistrationRepository.instance

    fun getName(): LiveData<String> {
        return repository.name
    }

    fun getEmail(): LiveData<String> {
        return repository.email
    }

    fun getPassword(): LiveData<String> {
        return repository.password
    }

    fun getRepeatPassword(): LiveData<String> {
        return repository.repPassword
    }

}