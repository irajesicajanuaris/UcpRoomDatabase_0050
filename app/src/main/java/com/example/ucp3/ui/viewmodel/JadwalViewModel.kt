package com.example.ucp3.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp3.data.entity.Jadwal
import com.example.ucp3.repository.RepositoryJadwal
import kotlinx.coroutines.launch


class JadwalViewModel(
    private val repositoryJadwal: RepositoryJadwal
) : ViewModel(){

    var uiState by mutableStateOf(JadwalUIState())

    //memperbarui state berdasarkan input pengguna
    fun updateState(JadwalEvent: JadwalEvent){
        uiState = uiState.copy(
            jadwalEvent = JadwalEvent,
        )
    }

    //validasi data input pengguna
    private fun validateFields(): Boolean{
        val event = uiState.jadwalEvent
        val errorState = FormJadwalErrorState(
            idJadwal = if (event.idJadwal.isNotEmpty()) null else "id Jadwal tidak boleh kosong",
            namaDokter = if (event.namaDokter.isNotEmpty()) null else "Nama Dokter tidak boleh kosong",
            namaPasien = if (event.namaPasien.isNotEmpty()) null else "Nama Pasien tidak boleh kosong",
            noHpPasien = if (event.noHpPasien.isNotEmpty()) null else "No Hp Pasien tidak boleh kosong",
            tanggalKonsultasi = if (event.tanggalKonsultasi.isNotEmpty()) null else "Tanggal Konsultasi tidak boleh kosong",
            status = if (event.status.isNotEmpty()) null else "Status tidak boleh kosong",

            )

        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isvalid()
    }

    //menyimpan data ke repository
    fun saveData() {
        val currentEvent = uiState.jadwalEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryJadwal.insertJadwal(currentEvent.toJadwalEntity())
                    uiState = uiState.copy(
                        snackbarMessage = "Data Berhasil disimpan",
                        jadwalEvent = JadwalEvent(), // Reset input form
                        isEntryValid = FormJadwalErrorState() // Reset error state
                    )
                } catch (e: Exception){
                    uiState = uiState.copy(
                        snackbarMessage = "Data gagal disimpan"
                    )
                }
            }
        } else {
            uiState = uiState.copy(
                snackbarMessage = "Input tidak valid. Periksa kembali data Anda"
            )
        }
    }
    // reset pesan snackbar setelah ditampilkan
    fun resetSnackBarMessage() {
        uiState = uiState.copy(snackbarMessage = null)
    }
}


data class JadwalUIState(
    val jadwalEvent: JadwalEvent = JadwalEvent(),
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

fun JadwalEvent.toJadwalEntity(): Jadwal = Jadwal (
    idJadwal = idJadwal,
    namaDokter = namaDokter,
    namaPasien = namaPasien,
    noHpPasien = noHpPasien,
    tanggalKonsultasi = tanggalKonsultasi,
    status = status
)