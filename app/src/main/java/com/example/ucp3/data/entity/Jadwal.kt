package com.example.ucp3.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jadwal")
data class Jadwal(
    @PrimaryKey
    val idJadwal: String,
    val namaDokter: String,
    val namaPasien: String,
    val noHp: String,
    val TanggalKonsultasi: String,
    val Status: String
)