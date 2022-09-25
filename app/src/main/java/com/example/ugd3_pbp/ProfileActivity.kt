package com.example.ugd3_pbp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ugd3_pbp.room.userDB

class ProfileActivity : AppCompatActivity() {

    val db by lazy{userDB(this)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
    }
}