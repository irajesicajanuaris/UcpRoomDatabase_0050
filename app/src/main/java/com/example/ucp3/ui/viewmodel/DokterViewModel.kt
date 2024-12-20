package com.example.ucp3.ui.viewmodel

import com.example.ucp3.data.entity.Dokter

data class DokterEvent(
    val idDokter: String,
    val nama: String,
    val spesialis: String,
    val klinik: String,
    val noHp: String,
    val jamKerja: String
)

//menyimpan input form ke dalam entity
fun DokterEvent.toMahasiswaEntity(): Mahasiswa = Mahasiswa (
    idDokter = idDokter,
    nama = nama,
    spesialis = spesialis,
    klinik = klinik,
    noHp = noHp,
    jamKerja = jamKerja
)