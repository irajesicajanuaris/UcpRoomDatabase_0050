package com.example.ucp3.ui.viewmodel

import com.example.ucp3.data.entity.Jadwal

class JadwalViewModel {

}


data class JadwalUIState(
    val JadwalEvent: JadwalEvent = JadwalEvent(),
    val isEntryValid: FormJadwalErrorState = FormJadwalErrorState(),
    val snackbarMessage: String? = null,
)

data class FormJadwalErrorState(
    val idJadwal: String? = null,
    val namaDokter: String? = null,
    val namaPasien: String? = null,
    val noHpPasien: String? = null,
    val tanggalKonsultasi: String? = null,
    val status: String? = null,
){
    fun isvalid(): Boolean{
        return idJadwal == null
                && namaDokter == null
                && namaPasien == null
                && noHpPasien == null
                && tanggalKonsultasi == null
                && status == null
    }
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