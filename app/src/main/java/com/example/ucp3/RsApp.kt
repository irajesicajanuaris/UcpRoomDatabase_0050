package com.example.ucp3

import android.app.Application
import com.example.ucp3.dependeciesinjection.ContainerApp

class RsApp : Application(){
    lateinit var containerApp: ContainerApp // fungsinya untuk menyimpan instance

    override fun onCreate() {
        super.onCreate()
        containerApp = ContainerApp(this) // membuat instance ContainerApp
        //instance adalah object yang dibuat dari class
    }
}