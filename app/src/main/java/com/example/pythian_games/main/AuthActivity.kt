package com.example.pythian_games.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.pythian_games.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val navController = findNavController(R.id.nav_host_auth_fragment)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav_auth_view)

        bottomNav?.setupWithNavController(navController)
    }
}