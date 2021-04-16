package com.example.pythian_games.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.pythian_games.R
import com.example.pythian_games.data.User
import com.example.pythian_games.firebaseDB.FirebaseDB
import com.example.pythian_games.viewModels.LoginViewModel
import com.example.pythian_games.viewModels.RegistrationViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class AuthActivity : AppCompatActivity() {
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val navController = findNavController(R.id.nav_host_auth_fragment)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav_auth_view)

        bottomNav?.setupWithNavController(navController)


        if (auth.currentUser != null) {
            val intent = Intent(this, StartActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun onLoginClicked(view: View) {
        val viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        val email = viewModel.getEmail().value
        val password = viewModel.getPassword().value
        if (email?.isNotEmpty() == true && password?.isNotEmpty() == true) {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    val intent = Intent(applicationContext, StartActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(applicationContext, "Email or password is incorrect!", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(applicationContext, "Missing data!", Toast.LENGTH_SHORT).show()
        }
    }

    fun onRegistrationClicked(view: View) {
        val viewModel = ViewModelProvider(this).get(RegistrationViewModel::class.java)
        val firebase = FirebaseDB()
        val name = viewModel.getName().value
        val email = viewModel.getEmail().value
        val password = viewModel.getPassword().value
        val repPassword = viewModel.getRepeatPassword().value
        if (name.isNullOrEmpty() || email.isNullOrEmpty() || password.isNullOrEmpty() || repPassword.isNullOrEmpty()) {
            Toast.makeText(applicationContext, "Missing information!", Toast.LENGTH_SHORT).show()
        } else if (password.equals(repPassword)) {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    val currUser = auth.currentUser
                    val user = User(currUser!!.uid, name, email)
                    firebase.createUser(user)
                    Toast.makeText(applicationContext, "Registration succeeded!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(applicationContext, StartActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        } else {
            Toast.makeText(applicationContext, "Passwords are not the same!", Toast.LENGTH_SHORT).show()
        }
    }
}