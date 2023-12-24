package com.example.kontrolajpc.database

import androidx.room.TypeConverter
import java.util.Date

class DateConverter1 {
    @TypeConverter
    fun toDate(date: Long?) : Date? {
        return date?.let { Date(it) }
    }

    @TypeConverter
    fun fromDate(date: Date?) : Long? {
        return date?.time
    }
}