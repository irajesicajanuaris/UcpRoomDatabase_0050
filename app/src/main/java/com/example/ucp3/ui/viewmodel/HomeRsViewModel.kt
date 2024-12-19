package com.example.ucp3.ui.viewmodel

import com.example.ucp3.data.entity.Dokter

data class HomeUiState(
    val listMhs: List<Dokter> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)