package com.example.pythian_games.main

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.pythian_games.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val navController = findNavController(R.id.nav_host_start_fragment)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav_start_view)

        bottomNav?.setupWithNavController(navController)

    }
}