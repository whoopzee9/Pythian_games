package com.example.pythian_games.repositories

import androidx.lifecycle.MutableLiveData

class RegistrationRepository {
    companion object {
        val instance : RegistrationRepository by lazy { holder.Instance }
    }

    private object holder {
        val Instance = RegistrationRepository()
    }

    var name: MutableLiveData<String> = MutableLiveData()
    var email: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()
    var repPassword: MutableLiveData<String> = MutableLiveData()

    init {

    }
}