package com.example.ucp3.repository

import com.example.ucp3.data.dao.DokterDao
import com.example.ucp3.data.dao.JadwalDao
import com.example.ucp3.data.entity.Dokter
import com.example.ucp3.data.entity.Jadwal
import kotlinx.coroutines.flow.Flow

class LocalRepository (
    private val dokterDao: DokterDao,
    private val jadwalDao: JadwalDao
) : Repository {

    override suspend fun insertDokter(dokter: Dokter) {
        dokterDao.insertDokter(dokter)
    }

    override fun getAllDokter(): Flow<List<Dokter>> {
        return dokterDao.getAllDokter()
    }

    override suspend fun insertJadwal(jadwal: Jadwal) {
        jadwalDao.insertJadwal(jadwal)
    }

    override fun getAllJadwal(): Flow<List<Jadwal>> {
        return jadwalDao.getAllJadwal()
    }

    override fun getJadwal(idJadwal: Int): Flow<Jadwal> {
        return jadwalDao.getJadwal(idJadwal)
    }

    override suspend fun deleteJadwal(jadwal: Jadwal) {
        jadwalDao.deleteJadwal(jadwal)
    }

    override suspend fun updateJadwal(jadwal: Jadwal) {
        jadwalDao.updateJadwal(jadwal)
    }
}