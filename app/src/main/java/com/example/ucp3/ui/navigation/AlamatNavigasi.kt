package com.example.ucp3.ui.navigation


interface AlamatNavigasi {
    val route: String
}

object DestinasiHome : AlamatNavigasi{
    override val route = "Home"
}

object DestinasiDetail : AlamatNavigasi {
    override val route = "detail"
    const val IdJadwal = "idJadwal"
    val routesWithArg = "$route/{$IdJadwal}"
}

object DestinasiUpdate : AlamatNavigasi {
    override val route = "update"
    const val IdJadwal = "idJadwal"
    val routesWithArg = "$route/{$IdJadwal}"
}