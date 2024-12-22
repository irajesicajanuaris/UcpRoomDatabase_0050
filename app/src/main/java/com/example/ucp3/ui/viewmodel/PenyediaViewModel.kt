package com.example.ucp3.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp3.RsApp


object PenyediaViewModel{

    val Factory = viewModelFactory {
        initializer {
            DokterViewModel(
                rsApp().containerApp.repositoryDokter
            )
        }
        initializer {
            HomeRsViewModel(
                rsApp().containerApp.repositoryDokter
            )
        }
        initializer {
            JadwalViewModel(
                rsApp().containerApp.repositoryJadwal
            )
        }
        initializer {
            HomeJadwalViewModel(
                rsApp().containerApp.repositoryJadwal
            )
        }
        initializer {
            DetailJadwalViewModel(
                createSavedStateHandle(),
                rsApp().containerApp.repositoryJadwal
            )
        }
        initializer {
            UpdateJadwalViewModel(
                createSavedStateHandle(),
                rsApp().containerApp.repositoryJadwal
            )
        }
    }
}

fun CreationExtras.rsApp(): RsApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as RsApp)

