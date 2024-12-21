package com.example.ucp3.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp3.data.entity.Jadwal
import com.example.ucp3.repository.RepositoryJadwal
import com.example.ucp3.ui.navigation.DestinasiUpdate
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


class UpdateJadwalViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryJadwal: RepositoryJadwal
): ViewModel() {

    var updateUIState by mutableStateOf(JadwalUIState())
        private set

    private val _idJadwal: String = checkNotNull(savedStateHandle[DestinasiUpdate.IdJadwal])

    init {
        viewModelScope.launch {
            updateUIState = repositoryJadwal.getJadwal(_idJadwal)
                .filterNotNull()
                .first()
                .toUIStateJadwal()
        }
    }

    fun updateState(jadwalEvent: JadwalEvent) {
        updateUIState = updateUIState.copy(
            jadwalEvent = jadwalEvent,
        )
    }

    fun validateFields(): Boolean {
        val event = updateUIState.jadwalEvent
        val errorState = FormJadwalErrorState(
            idJadwal = if (event.idJadwal.isNotEmpty()) null else "id Jadwal tidak boleh kosong",
            namaDokter = if (event.namaDokter.isNotEmpty()) null else "Nama Dokter tidak boleh kosong",
            namaPasien = if (event.namaPasien.isNotEmpty()) null else "Nama Pasien tidak boleh kosong",
            noHpPasien = if (event.noHpPasien.isNotEmpty()) null else "No Hp Pasien tidak boleh kosong",
            tanggalKonsultasi = if (event.tanggalKonsultasi.isNotEmpty()) null else "Tanggal Konsultasi tidak boleh kosong",
            status = if (event.status.isNotEmpty()) null else "Status tidak boleh kosong",
        )

        updateUIState = updateUIState.copy(isEntryValid = errorState)
        return errorState.isvalid()
    }

    fun updateData() {
        val currentEvent = updateUIState.jadwalEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryJadwal.updateJadwal(currentEvent.toJadwalEntity())
                    updateUIState = updateUIState.copy(
                        snackbarMessage = "Data berhasil diupdate",
                        jadwalEvent  = JadwalEvent(),
                        isEntryValid = FormJadwalErrorState()
                    )
                    println("snackBarMessage diatur: ${updateUIState.snackbarMessage}")
                } catch (e: Exception) {
                    updateUIState = updateUIState.copy(
                        snackbarMessage = "Data gagal diupdate"
                    )
                }
            }
        } else {
            updateUIState = updateUIState.copy(
                snackbarMessage = "Data gagal diupdate"
            )
        }
    }

    fun resetSnackBarMessage() {
        updateUIState = updateUIState.copy(snackbarMessage = null)
    }
}

fun Jadwal.toUIStateJadwal(): JadwalUIState = JadwalUIState(
    jadwalEvent =  this.toDetailUiEvent(),
)


