package com.example.category.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.category.Dao.CustomDao
import com.example.category.Model.CustomItem

@Database(entities = [CustomItem::class], version = 1)
abstract class CustomDatabase : RoomDatabase() {
    abstract fun customDao(): CustomDao

    companion object {
        private var instance: CustomDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): CustomDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext,
                        CustomDatabase::class.java, "custom_database")
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return instance!!
        }


    }

}