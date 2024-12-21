package com.example.ucp3.ui.viewmodel

import com.example.ucp3.data.entity.Jadwal

class JadwalViewModel {

}
data class JadwalEvent(
    val idJadwal: String = "",
    val namaDokter: String = "",
    val namaPasien: String = "",
    val noHpPasien: String = "",
    val tanggalKonsultasi: String = "",
    val status: String = "",
)

fun JadwalEvent.toMJadwalEntity(): Jadwal = Jadwal (
    idJadwal = idJadwal,
    namaDokter = namaDokter,
    namaPasien = namaPasien,
    noHpPasien = noHpPasien,
    tanggalKonsultasi = tanggalKonsultasi,
    status = status
)