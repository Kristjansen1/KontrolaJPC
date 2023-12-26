package com.example.kontrolajpc.presentation

import com.example.kontrolajpc.database.model.FaultModel
import com.example.kontrolajpc.util.DateUtil

data class FaultState(
    val faultList: List<FaultModel> = emptyList(),
    val date: Long = DateUtil.cDate(),
    val posel: String = "",
    val serijska: String = "",
    val proizvodnjiNalog: String = "",
    val vrstaNapake: Int = 0,
    val opisNapake: String = "",
    val dateDialogShowState: Boolean = false,
)