package com.example.kontrolajpc.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.Date

@Entity(tableName = "fault_table")
data class FaultModel(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    @ColumnInfo(name = "datum") var datum: Date,
    @ColumnInfo(name = "posel") var posel: String?,
    @ColumnInfo(name = "serijska") var serijska: String?,
    @ColumnInfo(name = "faultCode") var napaka: Int? = 0,
    @ColumnInfo(name = "opomba") var opomba: String?,
    @ColumnInfo(name = "exported") var exported: Boolean = false
) {
}