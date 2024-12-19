package com.example.ucp3.dependeciesinjection

import android.content.Context
import com.example.ucp3.data.database.RsDatabase
import com.example.ucp3.repository.LocalRepositoryDokter
import com.example.ucp3.repository.LocalRepositoryJadwal
import com.example.ucp3.repository.RepositoryDokter
import com.example.ucp3.repository.RepositoryJadwal

interface InterfaceContainerApp {
    val repositoryDokter: RepositoryDokter
    val repositoryJadwal: RepositoryJadwal
}

class ContainerApp(private val context: Context) : InterfaceContainerApp{
    override val repositoryDokter by lazy {
        LocalRepositoryDokter(RsDatabase.getDatabase(context).dokterDao())
    }
    override val repositoryJadwal by lazy {
        LocalRepositoryJadwal(RsDatabase.getDatabase(context).jadwalDao())
    }
}

