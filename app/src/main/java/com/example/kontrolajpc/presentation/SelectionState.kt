package com.example.kontrolajpc.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.kontrolajpc.database.model.FaultModel

data class SelectionState(
    val listOfSelectedFaultIds: SnapshotStateList<Int> = mutableStateListOf(),
    val enableMultipleSelection: Boolean = false
)