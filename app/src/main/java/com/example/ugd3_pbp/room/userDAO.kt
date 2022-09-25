package com.example.ugd3_pbp.room

import androidx.room.*



@Dao
interface userDAO {

        @Insert
        suspend fun addUser(data: userData)

        @Insert
        suspend fun updateUser(data: userData)

        @Delete
        suspend fun deleteUser(data: userData)

        @Query("SELECT * FROM userData")
        suspend fun getNotes() : List<userData>

        @Query("SELECT * FROM userData WHERE username =:username")
        suspend fun getNote(note_id: Int) : List<userData>

}