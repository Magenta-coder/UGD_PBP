package com.example.ugd3_pbp.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Obat (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String
)