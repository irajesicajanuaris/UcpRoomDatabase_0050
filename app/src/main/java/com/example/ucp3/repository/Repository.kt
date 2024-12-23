package com.example.ucp3.repository

import com.example.ucp3.data.entity.Dokter
import com.example.ucp3.data.entity.Jadwal
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun insertDokter(dokter: Dokter)

    fun getAllDokter(): Flow<List<Dokter>>

    suspend fun insertJadwal(jadwal: Jadwal)
    fun getAllJadwal(): Flow<List<Jadwal>>
    fun getJadwal(idJadwal: Int): Flow<Jadwal>
    suspend fun deleteJadwal(jadwal: Jadwal)
    suspend fun updateJadwal(jadwal: Jadwal)
}