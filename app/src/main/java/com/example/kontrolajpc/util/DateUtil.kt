package com.example.kontrolajpc.util

import android.util.Log
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


object DateUtil {
    fun fromLongToDate(long: Long,format: String): String {
        val sdf = SimpleDateFormat(format, Locale.FRANCE)
        Log.d("datum", sdf.format(long))
        return sdf.format(long)
    }

    fun cDate(): Long {
        val c = Calendar.getInstance()
        Log.d("datum1", c.timeInMillis.toString())
        return c.timeInMillis
    }

    fun cDateWithFormat(format: String) : String {
         return fromLongToDate(cDate(),format)
    }
}