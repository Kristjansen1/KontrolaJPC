package com.example.kontrolajpc.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "fault_table")
data class FaultModel(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "datum") var datum: Long = 0,
    @ColumnInfo(name = "posel") var posel: String?,
    @ColumnInfo(name = "serijska") var serijska: String?,
    @ColumnInfo(name = "proizvodniNalog") var proizvodniNalog: String?,
    @ColumnInfo(name = "vrstaNapake") var vrstaNapake: Int? = 0,
    @ColumnInfo(name = "opisNapake") var opisNapake: String?,
    @ColumnInfo(name = "exported") var exported: Boolean = false
)