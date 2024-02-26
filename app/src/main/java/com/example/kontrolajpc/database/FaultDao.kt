package com.example.kontrolajpc.database

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.kontrolajpc.database.model.FaultModel
import kotlinx.coroutines.flow.Flow

@Dao
interface FaultDao {

    @Upsert
    suspend fun upsert(fault: FaultModel)

    @Query("SELECT * FROM fault_table ORDER BY datum asc")
    fun getAllFaults(): Flow<List<FaultModel>>

    @Delete
    suspend fun delete(fault: FaultModel)

    @Query("DELETE FROM fault_table where id in (:idList)")
    suspend fun deleteById(idList: SnapshotStateList<Int>)

    @Query("SELECT * FROM fault_table where id in (:idList)")
    suspend fun findByID(idList: SnapshotStateList<Int>): List<FaultModel>

    @Query("UPDATE fault_table SET exported =:exported WHERE id in (:idList)")
    suspend fun setExportedById(idList: SnapshotStateList<Int>,exported: Boolean)

    @Query("DELETE FROM fault_table WHERE exported = 1")
    suspend fun deleteExported()
}