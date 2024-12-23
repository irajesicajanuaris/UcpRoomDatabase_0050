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
                rsApp().containerApp.repository
            )
        }
        initializer {
            HomeRsViewModel(
                rsApp().containerApp.repository
            )
        }
        initializer {
            JadwalViewModel(
                rsApp().containerApp.repository
            )
        }
        initializer {
            HomeJadwalViewModel(
                rsApp().containerApp.repository
            )
        }
        initializer {
            DetailJadwalViewModel(
                createSavedStateHandle(),
                rsApp().containerApp.repository
            )
        }
        initializer {
            UpdateJadwalViewModel(
                createSavedStateHandle(),
                rsApp().containerApp.repository
            )
        }
    }
}

fun CreationExtras.rsApp(): RsApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as RsApp)

