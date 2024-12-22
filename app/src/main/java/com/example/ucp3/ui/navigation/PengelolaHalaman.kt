package com.example.ucp3.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ucp3.ui.View.Dokter.DestinasiInsertDokter
import com.example.ucp3.ui.View.Dokter.HomeDokterView
import com.example.ucp3.ui.View.Dokter.InsertDokterView
import com.example.ucp3.ui.View.Jadwal.DestinasiInsertJadwal
import com.example.ucp3.ui.View.Jadwal.DetailJadwalView
import com.example.ucp3.ui.View.Jadwal.HomeJadwalView
import com.example.ucp3.ui.View.Jadwal.InsertJadwalView
import com.example.ucp3.ui.View.Jadwal.UpdateJadwalView


@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHomeDokter.route
    ) {
        composable(
            route = DestinasiHomeDokter.route
        ) {
            HomeDokterView(
                onAddDokter = {
                    navController.navigate(DestinasiInsertDokter.route)
                },
                onSeeJadwal = {
                    navController.navigate(DestinasiHomeJadwal.route)
                },
                modifier = Modifier
            )
        }

        composable(
            route = DestinasiInsertDokter.route
        ) {
            InsertDokterView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }

        composable(
            route = DestinasiHomeJadwal.route
        ) {
            HomeJadwalView(
                onAddJadwal = {
                    navController.navigate(DestinasiInsertJadwal.route)
                },
                onSeeDokter = {
                    navController.navigate(DestinasiHomeDokter.route)
                },
                onDetailClick = { idJadwal ->
                    navController.navigate("${DestinasiDetail.route}/$idJadwal")
                    println("PengelolaHalaman: idJadwal = $idJadwal")
                },
                modifier = modifier)
        }
        composable(
            route = DestinasiInsertJadwal.route
        ) {
            InsertJadwalView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }
        composable(
            DestinasiDetail.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetail.IdJadwal){
                    type = NavType.StringType
                }
            )
        ){
            val noJdwl = it.arguments?.getString(DestinasiDetail.IdJadwal)
            noJdwl?.let { noJdwl ->
                DetailJadwalView(
                    onBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdate.route}/$it")
                    },
                    modifier = modifier,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable(
            DestinasiUpdate.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdate.IdJadwal){
                    type = NavType.StringType
                }
            )
        ){
            UpdateJadwalView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }
    }
}

