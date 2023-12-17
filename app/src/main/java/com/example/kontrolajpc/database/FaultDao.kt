package com.example.kontrolajpc.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kontrolajpc.model.FaultModel

@Dao
abstract class FaultDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(fault: FaultModel)

    @Query("SELECT * FROM fault_table")
    abstract fun getAllFaults(): LiveData<List<FaultModel>>
}