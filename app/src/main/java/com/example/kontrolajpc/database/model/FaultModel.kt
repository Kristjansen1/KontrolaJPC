package com.example.kontrolajpc.database.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import kotlinx.parcelize.Parcelize


@Entity(tableName = "fault_table")
data class FaultModel(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "datum") var datum: Long = 0,
    @ColumnInfo(name = "posel") var posel: String?,
    @ColumnInfo(name = "serijska") var serijska: String?,
    @ColumnInfo(name = "faultCode") var napaka: Int? = 0,
    @ColumnInfo(name = "opomba") var opomba: String?,
    @ColumnInfo(name = "exported") var exported: Boolean = false
)