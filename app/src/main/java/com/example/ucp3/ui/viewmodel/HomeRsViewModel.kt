package com.example.ucp3.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp3.data.entity.Dokter
import com.example.ucp3.repository.Repository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class HomeRsViewModel (
    private val repository: Repository
): ViewModel() {

    val homeUiState: StateFlow<HomeRsUiState> = repository.getAllDokter()
        .filterNotNull()
        .map {
            HomeRsUiState(
                listDokter = it.toList(),
                isLoading = false,
            )
        }
        .onStart {
            emit(HomeRsUiState(isLoading = true))
            delay(900)
        }
        .catch {
            emit(
                HomeRsUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi Kesalahan",
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeRsUiState(
                isLoading = true
            )
        )
}

data class HomeRsUiState(
    val listDokter: List<Dokter> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)