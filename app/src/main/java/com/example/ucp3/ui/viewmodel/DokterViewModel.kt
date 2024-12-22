package com.example.ucp3.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp3.data.entity.Dokter
import com.example.ucp3.repository.RepositoryDokter
import kotlinx.coroutines.launch

class DokterViewModel(
    private val repositoryDokter: RepositoryDokter
) : ViewModel(){

    var uiState by mutableStateOf(DokteruiState())

    //memperbarui state berdasarkan input pengguna
    fun updateState(DokterEvent: DokterEvent){
        uiState = uiState.copy(
            dokterEvent = DokterEvent,
        )
    }

    //validasi data input pengguna
    private fun validateFields(): Boolean{
        val event = uiState.dokterEvent
        val errorState = FormErrorStateDokter(
            idDokter = if (event.idDokter.isNotEmpty()) null else "Id Dokter tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            spesialis = if (event.spesialis.isNotEmpty()) null else "Spesialis tidak boleh kosong",
            klinik = if (event.klinik.isNotEmpty()) null else "Klinik tidak boleh kosong",
            noHpDokter = if (event.noHpDokter.isNotEmpty()) null else "No Hp tidak boleh kosong",
            jamKerja = if (event.jamKerja.isNotEmpty()) null else "Jam Kerja tidak boleh kosong",

            )

        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isvalid()
    }

    //menyimpan data ke repository
    fun saveData() {
        val currentEvent = uiState.dokterEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryDokter.insertDokter(currentEvent.toDokterEntity())
                    uiState = uiState.copy(
                        snackbarMessage = "Data Berhasil disimpan",
                        dokterEvent = DokterEvent(), // Reset input form
                        isEntryValid = FormErrorStateDokter() // Reset error state
                    )
                } catch (e: Exception){
                    uiState = uiState.copy(
                        snackbarMessage = "Data gagal disimpan"
                    )
                }
            }
        } else {
            uiState = uiState.copy(
                snackbarMessage = "Input tidak valid. Periksa kelmbali data Anda"
            )
        }
    }
    // reset pesan snackbar setelah ditampilkan
    fun resetSnackBarMessage() {
        uiState = uiState.copy(snackbarMessage = null)
    }
}


data class DokteruiState(
    val dokterEvent: DokterEvent = DokterEvent(),
    val isEntryValid: FormErrorStateDokter = FormErrorStateDokter(),
    val snackbarMessage: String? = null,
)

data class FormErrorStateDokter(
    val idDokter: String? = null,
    val nama: String? = null,
    val spesialis: String? = null,
    val klinik: String? = null,
    val noHpDokter: String? = null,
    val jamKerja: String? = null,
){
    fun isvalid(): Boolean{
        return idDokter == null
                && nama == null
                && spesialis == null
                && klinik == null
                && noHpDokter == null
                && jamKerja == null
    }
}

data class DokterEvent(
    val idDokter: String = "",
    val nama: String = "",
    val spesialis: String = "",
    val klinik: String = "",
    val noHpDokter: String = "",
    val jamKerja: String = ""
)

//menyimpan input form ke dalam entity
fun DokterEvent.toDokterEntity(): Dokter = Dokter(
    idDokter = idDokter,
    nama = nama,
    spesialis = spesialis,
    klinik = klinik,
    noHpDokter = noHpDokter,
    jamKerja = jamKerja
)