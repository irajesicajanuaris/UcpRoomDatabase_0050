package com.example.ucp3.dependeciesinjection

import android.content.Context
import com.example.ucp3.data.database.RsDatabase
import com.example.ucp3.repository.LocalRepository
import com.example.ucp3.repository.Repository

interface InterfaceContainerApp {
    val repository: Repository
}

class ContainerApp(private val context: Context) : InterfaceContainerApp{
    override val repository by lazy {
        LocalRepository(RsDatabase.getDatabase(context).dokterDao(),
            RsDatabase.getDatabase(context).jadwalDao())
    }
}

