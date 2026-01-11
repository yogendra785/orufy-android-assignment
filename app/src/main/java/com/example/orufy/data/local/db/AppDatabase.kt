package com.example.orufy.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.orufy.data.local.dao.UrlHistoryDao
import com.example.orufy.data.local.entity.UrlHistoryEntity

@Database(
    entities = [UrlHistoryEntity:: class],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun urlHistoryDao(): UrlHistoryDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "orufy_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}