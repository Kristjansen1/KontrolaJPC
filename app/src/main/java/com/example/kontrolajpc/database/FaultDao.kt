package com.example.kontrolajpc.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kontrolajpc.database.model.FaultModel
import kotlinx.coroutines.flow.Flow

@Dao
interface FaultDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(fault: FaultModel)

    @Query("SELECT * FROM fault_table ORDER BY datum asc")
    abstract fun getAllFaults(): Flow<List<FaultModel>>

    @Delete
    suspend fun delete(fault: FaultModel)
}