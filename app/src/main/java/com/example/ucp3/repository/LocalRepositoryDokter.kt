package com.example.ucp3.repository

import com.example.ucp3.data.dao.DokterDao
import com.example.ucp3.data.entity.Dokter
import kotlinx.coroutines.flow.Flow

class LocalRepositoryDokter(
    private val dokterDao: DokterDao
) : RepositoryDokter {

    override suspend fun insertDokter(dokter: Dokter) {
        dokterDao.insertDokter(dokter)
    }

    override fun getAllDokter(): Flow<List<Dokter>> {
        return dokterDao.getAllDokter()
    }

    override fun getDokter(idDokter: String): Flow<Dokter> {
        return dokterDao.getDokter(idDokter)
    }
}