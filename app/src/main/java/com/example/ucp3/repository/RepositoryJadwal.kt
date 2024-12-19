package com.example.ucp3.repository

import com.example.ucp3.data.entity.Jadwal
import kotlinx.coroutines.flow.Flow

interface RepositoryJadwal {
    suspend fun insertJadwal(jadwal: Jadwal)
    fun getAllJadwal(): Flow<List<Jadwal>>
    fun getJadwal(idJadwal: String): Flow<Jadwal>
    suspend fun deleteJadwal(jadwal: Jadwal)
    suspend fun updateJadwal(jadwal: Jadwal)
}