package com.example.ucp3.repository

import com.example.ucp3.data.entity.Dokter
import kotlinx.coroutines.flow.Flow

interface RepositoryDokter {
    suspend fun insertDokter(dokter: Dokter)

    fun getAllDokter(): Flow<List<Dokter>>
    fun getDokter(idDokter: String): Flow<Dokter>
}