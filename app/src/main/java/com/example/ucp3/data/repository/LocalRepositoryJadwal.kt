package com.example.ucp3.data.repository

import com.example.ucp3.data.dao.JadwalDao
import com.example.ucp3.data.entity.Jadwal
import kotlinx.coroutines.flow.Flow

class LocalRepositoryJadwal(
    private val jadwalDao: JadwalDao
) : RepositoryJadwal {

    override suspend fun insertJadwal(jadwal: Jadwal) {
        jadwalDao.insertJadwal(jadwal)
    }

    override fun getAllJadwal(): Flow<List<Jadwal>> {
        return jadwalDao.getAllJadwal()
    }

    override fun getJadwal(idJadwal: String): Flow<Jadwal> {
        return jadwalDao.getJadwal(idJadwal)
    }

    override suspend fun deleteJadwal(jadwal: Jadwal) {
        jadwalDao.deleteJadwal(jadwal)
    }

    override suspend fun updateJadwal(jadwal: Jadwal) {
        jadwalDao.updateJadwal(jadwal)
    }
}