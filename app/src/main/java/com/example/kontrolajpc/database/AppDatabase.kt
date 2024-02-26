package com.example.kontrolajpc.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kontrolajpc.database.model.FaultModel

@Database(entities = [FaultModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(

) {
    abstract fun faultDao(): FaultDao
}