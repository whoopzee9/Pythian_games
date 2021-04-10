package com.example.pythian_games.firebaseDB

import com.example.pythian_games.data.User

interface ExtensionsCRUD {
    fun createRoom(name: String, secretCode: String)

    fun createUser(userData: User)
}