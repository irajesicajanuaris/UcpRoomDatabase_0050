package com.example.ucp3.ui.View.Dokter

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp3.R
import com.example.ucp3.data.entity.Dokter
import com.example.ucp3.ui.viewmodel.HomeRsUiState
import com.example.ucp3.ui.viewmodel.HomeRsViewModel
import com.example.ucp3.ui.viewmodel.PenyediaViewModel

@Composable
fun HomeDokterView(
    viewModel: HomeRsViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onAddDokter: () -> Unit = { },
    onSeeJadwal: () -> Unit = { },
    onSeeDokter: () -> Unit = {},
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.teal_700))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.kesehatan),
                contentDescription = "",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(60.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Rumah Sehat",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    color = Color.White
                )
                Text(
                    text = "Mari Hidup Sehat Bersama",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    color = Color.White
                )
            }
            Image(
                painter = painterResource(id = R.drawable.karina),
                contentDescription = "Profil User",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
            )
        }

        Box(
            modifier = Modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                )
                .fillMaxSize()
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "Daftar Dokter",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = onSeeDokter,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.teal_700),
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Lihat Dokter")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = onSeeJadwal,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.teal_700),
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Lihat Jadwal")
                    }
                }

                val homeUiState by viewModel.homeUiState.collectAsState()
                BodyHomeDokterView(
                    homeRsUiState = homeUiState,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

            }
            Column(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp).padding(bottom = 40.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                FloatingActionButton(
                    onClick = onSeeJadwal,
                    shape = CircleShape,
                    containerColor = colorResource(id = R.color.teal_200),
                    contentColor = Color.White
                ) {
                    Icon(
                        imageVector = Icons.Default.List,
                        contentDescription = "Lihat Jadwal"
                    )
                }
                FloatingActionButton(
                    onClick = onAddDokter,
                    shape = CircleShape,
                    containerColor = colorResource(id = R.color.teal_200),
                    contentColor = Color.White
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Tambah Dokter"
                    )
                }
            }

        }
    }
}

@Composable
fun BodyHomeDokterView(
    homeRsUiState: HomeRsUiState,
    onClick: (Int) -> Unit = { },
    modifier: Modifier = Modifier
) {
    when {
        homeRsUiState.isLoading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        homeRsUiState.isError -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Terjadi kesalahan. Coba lagi nanti.",
                    color = Color.Red
                )
            }
        }
        homeRsUiState.listDokter.isEmpty() -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tidak Ada Data Dokter.",
                    fontWeight = FontWeight.Bold
                )
            }
        }
        else -> {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                items(homeRsUiState.listDokter) { dok ->
                    CardDokter(dok = dok, onClick = { onClick(dok.idDokter) })
                }
            }
        }
    }
}


fun getWarnaSpesialis(spesialis: String): Color{
    return when (spesialis){
        "Dokter Umum" -> Color.Gray
        "Dokter Bedah" -> Color.Red
        "Dokter Saraf dan Neurologis" -> Color.Blue
        "Dokter Penyakit Dalam"-> Color.Cyan
        "Dokter Gigi" -> Color.Green
        else -> Color.LightGray
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardDokter(
    dok: Dokter,
    onClick: () -> Unit = { },
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(30.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "Ikon Dokter",
                tint = colorResource(id = R.color.teal_700),
                modifier = Modifier.size(90.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = dok.nama,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    color = Color.Black
                )

                Text(
                    text = "Spesialis: ${dok.spesialis}",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp,
                    color = getWarnaSpesialis(dok.spesialis)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Filled.Home,
                        contentDescription = "ikon Klinik",
                        tint = Color(0xFF7DDFCC),
                        modifier = Modifier.size(17.dp))
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        text = dok.klinik,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Filled.DateRange,
                        contentDescription = "ikon Jam Kerja",
                        tint = Color(0xFF7DDFCC),
                        modifier = Modifier.size(17.dp))
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        text = dok.jamKerja,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp
                    )
                }
            }
        }
    }
}