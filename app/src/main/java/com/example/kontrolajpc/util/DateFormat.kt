package com.example.kontrolajpc.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

object DateFormat {
    @SuppressLint("SimpleDateFormat")
    fun dateToFormat(date: Date): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        return sdf.format(date)
    }
}