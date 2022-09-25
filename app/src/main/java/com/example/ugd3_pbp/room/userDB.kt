package com.example.ugd3_pbp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
entities = [userData::class],
version = 1
)

abstract class userDB: RoomDatabase() {

    abstract fun userDao () : userDAO

    companion object {
        @Volatile private var instance : userDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?:
        synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                userDB::class.java,
                "userDatabase.db"

            ).build()
    }

}