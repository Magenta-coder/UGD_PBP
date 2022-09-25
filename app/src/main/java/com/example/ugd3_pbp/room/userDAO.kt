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
        suspend fun getUser() : List<userData>

        @Query("SELECT * FROM userData WHERE id = user_id")
        suspend fun getUser(user_id: Int) : List<userData>

}