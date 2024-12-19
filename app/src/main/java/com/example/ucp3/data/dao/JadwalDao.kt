package com.example.ucp3.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ucp3.data.entity.Jadwal
import kotlinx.coroutines.flow.Flow

@Dao
interface JadwalDao {
    @Insert
    suspend fun insertJadwal(jadwal: Jadwal)

    //getAllJadwal
    @Query("SELECT * FROM jadwal ORDER BY namaPasien ASC")
    fun getAllJadwal(): Flow<List<Jadwal>>

    //getJadwal
    @Query("SELECT * FROM jadwal WHERE idJadwal = :idJadwal")
    fun getJadwal(idJadwal: String): Flow<Jadwal>

    //deleteJadwal
    @Delete
    suspend fun deleteJadwal(jadwal: Jadwal)

    //updateJadwal
    @Update
    suspend fun updateJadwal(jadwal: Jadwal)
}