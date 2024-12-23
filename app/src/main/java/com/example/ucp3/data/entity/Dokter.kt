package com.example.ucp3.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dokter")
data class Dokter(
    @PrimaryKey(autoGenerate = true)
    val idDokter: Int = 0,
    val nama: String,
    val spesialis: String,
    val klinik: String,
    val noHpDokter: String,
    val jamKerja: String
)