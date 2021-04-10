package com.example.pythian_games.firebaseDB

import com.example.pythian_games.data.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class FirebaseDB: ExtensionsCRUD {

    private var gamesRef = FirebaseDatabase.getInstance().getReference("GAMES")
    private var usersRef = FirebaseDatabase.getInstance().getReference("USERS")
    private var questionsRef = FirebaseDatabase.getInstance().getReference("QUESTIONS")

    private var auth = FirebaseAuth.getInstance()
    private var user = auth.currentUser

    override fun createRoom(name: String, secretCode: String) {
        TODO("Not yet implemented")
    }

    override fun createUser(userData: User) {
        usersRef.child(userData.id).setValue(userData)
    }

}