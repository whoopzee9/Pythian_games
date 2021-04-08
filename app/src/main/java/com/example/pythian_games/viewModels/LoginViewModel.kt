package com.example.pythian_games.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.pythian_games.repositories.LoginRepository

class LoginViewModel : ViewModel() {
    var repository = LoginRepository.instance

    fun getEmail(): LiveData<String>  {
        return repository.email
    }

    fun getPassword(): LiveData<String> {
        return repository.password
    }
}