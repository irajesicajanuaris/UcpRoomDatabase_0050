package com.example.ucp3.ui.viewmodel

import com.example.ucp3.data.entity.Dokter


data class DokterUIState(
    val dokterEvent: DokterEvent = DokterEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackbarMessage: String? = null,
)

data class FormErrorState(
    val idDokter: String? = null,
    val nama: String? = null,
    val spesialis: String? = null,
    val klinik: String? = null,
    val noHp: String? = null,
    val jamKerja: String? = null,
){
    fun isvalid(): Boolean{
        return idDokter == null
                && nama == null
                && spesialis == null
                && klinik == null
                && noHp == null
                && jamKerja == null
    }
}

data class DokterEvent(
    val idDokter: String,
    val nama: String,
    val spesialis: String,
    val klinik: String,
    val noHp: String,
    val jamKerja: String
)

//menyimpan input form ke dalam entity
fun DokterEvent.toDokterEntity(): Dokter = Dokter (
    idDokter = idDokter,
    nama = nama,
    spesialis = spesialis,
    klinik = klinik,
    noHp = noHp,
    jamKerja = jamKerja
)