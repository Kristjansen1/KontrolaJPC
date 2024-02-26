package com.example.kontrolajpc.util

import android.os.Environment
import com.example.kontrolajpc.database.model.FaultModel

object Const {

    const val DATE_FORMAT_UI = "dd. MM. yyyy"
    const val DATE_FORMAT_EXPORT = "dd-MM-yy"


    val EMPTY_FAULT_MODEL: FaultModel = FaultModel(
        0,
        0,
        "",
        "",
        "",
        0,
        ""
    )
}