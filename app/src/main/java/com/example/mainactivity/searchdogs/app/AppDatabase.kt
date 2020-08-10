package com.example.mainactivity.searchdogs.app

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mainactivity.searchdogs.main.local.DogDao
import com.example.mainactivity.searchdogs.main.model.Dog

@Database(entities = [Dog::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_NAME: String = "app_db"
    }

    abstract fun getDogDao(): DogDao

}