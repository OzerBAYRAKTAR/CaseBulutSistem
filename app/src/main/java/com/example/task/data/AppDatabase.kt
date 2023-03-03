package com.example.task.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [RegisterModel::class], version = 1, exportSchema = true)
abstract class AppDatabase :RoomDatabase(){

    abstract fun registerDao(): RegisterDao



    //Singleton
    companion object {

        @Volatile private var instance : AppDatabase? = null

        private val lock = Any()

        operator fun invoke(context : Context) = instance ?: synchronized(lock) {
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        private fun makeDatabase(context : Context) = Room.databaseBuilder(
            context.applicationContext,AppDatabase::class.java,"resourcedatabase"
        )   .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
}