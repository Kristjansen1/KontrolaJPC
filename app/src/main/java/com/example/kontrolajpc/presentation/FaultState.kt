package com.example.kontrolajpc.presentation

import com.example.kontrolajpc.database.model.FaultModel
import com.example.kontrolajpc.util.DateUtil
import java.time.LocalDate
import java.time.LocalDateTime

data class FaultState(
    val FaultList: List<FaultModel> = emptyList(),
    val date: Long = DateUtil.cDate(),
    val posel: String = "",
    val serijska: String = "",
    val proizvodnjiNalog: String = "",
    val vrstaNapake: Int = 0,
    val opisNapake: String = "",
    val dateDialogShowState: Boolean = false,
    //var fault: FaultModel = FaultModel(0,0,"","",0,"")
)