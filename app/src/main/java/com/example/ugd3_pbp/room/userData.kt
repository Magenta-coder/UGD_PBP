package com.example.ugd3_pbp.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class userData (

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val username: String,
    val email: String,
    val date: String,
    val phonenum: Int,
    val password: String

)