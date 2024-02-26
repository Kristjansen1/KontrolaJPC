package com.example.kontrolajpc.presentation

import com.example.kontrolajpc.database.model.FaultModel
import com.example.kontrolajpc.util.DateUtil

data class FaultState(
    val faultList: List<FaultModel> = emptyList(),
    val id: Int = 0,
    val date: Long = DateUtil.cDate(),
    val posel: String = "",
    val serijska: String = "",
    val proizvodniNalog: String = "",
    val vrstaNapake: Int = 0,
    val opisNapake: String = "",
    val exported: Boolean = false,
    val dateDialogShowState: Boolean = false,
    val enableEdit: Boolean = false
)