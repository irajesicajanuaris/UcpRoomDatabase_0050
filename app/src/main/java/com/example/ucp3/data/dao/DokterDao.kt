package com.example.ucp3.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ucp3.data.entity.Dokter
import kotlinx.coroutines.flow.Flow

@Dao
interface DokterDao {
    @Insert
    suspend fun insertDokter(dokter: Dokter)

    //getAllDokter
    @Query("SELECT * FROM dokter ORDER BY nama ASC")
    fun getAllDokter(): Flow<List<Dokter>>

    //getMahasiswa
    @Query("SELECT * FROM dokter WHERE idDokter = :idDokter")
    fun getDokter(nim: String): Flow<Dokter>
}