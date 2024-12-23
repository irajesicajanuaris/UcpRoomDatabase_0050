package com.example.ucp3.ui.navigation


interface AlamatNavigasi {
    val route: String
}

object DestinasiHomeDokter : AlamatNavigasi{
    override val route = "Home_Dokter"
}

object DestinasiHomeJadwal : AlamatNavigasi{
    override val route = "Home_jadwal"
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