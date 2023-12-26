package com.example.kontrolajpc.util

import android.util.Log
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

const val DATE_FORMAT = "dd. MM. yyyy"

object DateUtil {
    fun fromLongToDate(long: Long): String {
        val sdf = SimpleDateFormat(DATE_FORMAT, Locale.FRANCE)
        Log.d("datum", sdf.format(long))
        return sdf.format(long)
    }

    fun cDate(): Long {
        val c = Calendar.getInstance()
        Log.d("datum1", c.timeInMillis.toString())
        return c.timeInMillis
    }
}