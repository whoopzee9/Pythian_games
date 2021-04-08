package com.example.pythian_games.repositories

import androidx.lifecycle.MutableLiveData

class LoginRepository {
    companion object {
        val instance : LoginRepository by lazy { holder.Instance }
    }

    private object holder {
        val Instance = LoginRepository()
    }

    var email: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()

    init {

    }
}