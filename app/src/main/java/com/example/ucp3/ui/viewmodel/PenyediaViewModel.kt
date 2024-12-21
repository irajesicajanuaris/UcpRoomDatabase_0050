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
                RsApp().containerApp.repositoryDokter
            )
        }
        initializer {
            HomeRsViewModel(
                RsApp().containerApp.repositoryDokter
            )
        }
        initializer {
            JadwalViewModel(
                RsApp().containerApp.repositoryJadwal
            )
        }
        initializer {
            HomeJadwalViewModel(
                RsApp().containerApp.repositoryJadwal
            )
        }
        initializer {
            DetailJadwalViewModel(
                createSavedStateHandle(),
                RsApp().containerApp.repositoryJadwal
            )
        }
        initializer {
            UpdateJadwalViewModel(
                createSavedStateHandle(),
                RsApp().containerApp.repositoryJadwal
            )
        }
    }
}

fun CreationExtras.rsApp(): RsApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as RsApp)

