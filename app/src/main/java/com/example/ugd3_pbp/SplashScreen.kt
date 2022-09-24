package com.example.ugd3_pbp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        val sharedPreferences = getSharedPreferences("prevStarted", MODE_PRIVATE)

        if (!sharedPreferences.getBoolean("prevStarted", false)){
            Handler().postDelayed({
                val intent = Intent(this@SplashScreen, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, 2000)

            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putBoolean("prevStarted", true)
            editor.apply()
        }else{
            val intent = Intent(this@SplashScreen, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}