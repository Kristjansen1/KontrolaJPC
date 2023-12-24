package com.example.kontrolajpc.presentation

import com.example.kontrolajpc.database.model.FaultModel
import java.time.LocalDate

data class FaultState(
    val FaultList: List<FaultModel> = emptyList(),
    val date: String = LocalDate.now().toString(),
    val posel: String = "",
    val serijska: String = "",
    val proizvodnjiNalog: String = "",
    val vrstaNapake: Int = 0,
    val opisNapake: String = "",
    val dateDialogShowState: Boolean = false
)