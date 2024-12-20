package com.example.ucp3.ui.navigation


interface AlamatNavigasi {
    val route: String
}

object DestinasiHome : AlamatNavigasi{
    override val route = "Home"
}
