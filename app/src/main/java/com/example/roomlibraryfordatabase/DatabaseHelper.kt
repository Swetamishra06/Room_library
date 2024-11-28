package com.example.roomlibraryfordatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Details::class], version = 1, exportSchema = false)
abstract class DatabaseHelper : RoomDatabase() {

    companion object {
        @Volatile
        private var instance: DatabaseHelper? = null

        private val LOCK = Any()

        fun getInstance(context: Context): DatabaseHelper {
            return instance ?: synchronized(LOCK) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseHelper::class.java,
                    "userDetails"
                ).fallbackToDestructiveMigration()
                    .build().also { instance = it }
            }
        }
    }

    // Define DAOs (Data Access Objects) here
    // abstract fun userDao(): UserDao

    // Define the abstract method for DAO
    abstract fun detailDao(): DetailDAO
}
