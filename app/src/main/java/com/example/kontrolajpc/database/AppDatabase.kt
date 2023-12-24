package com.example.kontrolajpc.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.kontrolajpc.database.model.FaultModel

@TypeConverters(value = [DateConverter1::class])
@Database(entities = [FaultModel::class],version = 1, exportSchema = true)
abstract class AppDatabase: RoomDatabase() {

    abstract fun  faultDao(): FaultDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "database"
                ).build()
                INSTANCE = instance

                instance
            }
        }
    }
}