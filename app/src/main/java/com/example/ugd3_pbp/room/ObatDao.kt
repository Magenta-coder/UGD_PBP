package com.example.ugd3_pbp.room

import androidx.room.*

@Dao
interface ObatDao {
    @Insert
    suspend fun addObat(obat: Obat)

    @Update
    suspend fun updateObat(obat: Obat)

    @Delete
    suspend fun deleteObat(obat: Obat)

    @Query("SELECT * FROM obat")
    suspend fun getObat() : List<Obat>

    @Query("SELECT * FROM obat WHERE id =:obat_id")
    suspend fun getObat(obat_id: Int) : List<Obat>
}