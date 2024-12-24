package com.example.ucp3.ui.View.Jadwal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp3.R
import com.example.ucp3.data.entity.Jadwal
import com.example.ucp3.ui.viewmodel.DetailJadwalViewModel
import com.example.ucp3.ui.viewmodel.PenyediaViewModel
import com.example.ucp3.ui.costumewidget.TopAppBar
import com.example.ucp3.ui.viewmodel.DetailUiState
import com.example.ucp3.ui.viewmodel.toJadwalEntity

@Composable
fun DetailJadwalView(
    modifier: Modifier = Modifier,
    viewModel: DetailJadwalViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onBack: () -> Unit = { },
    onEditClick: (String) -> Unit = { },
    onDeleteClick: () -> Unit = { },
) {
    Scaffold(
        topBar = {
            TopAppBar(
                judul = "Detail jadwal",
                showBackButton = true,
                onBack = onBack,
                modifier = Modifier.padding(top = 20.dp)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEditClick(viewModel.detailUIState.value.detailUiEvent.idJadwal.toString())
                },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(2.dp),
                containerColor = colorResource(id = R.color.teal_200),
                contentColor = Color.White
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Jadwal",
                )
            }
        }
    ) { innerPadding ->
        val detailUiState by viewModel.detailUIState.collectAsState()

        BodyDetailJadwal(
            modifier = Modifier.padding(innerPadding)
                .padding(top = 16.dp),
            detailUiState = detailUiState,
            onDeleteClick = {
                viewModel.deleteJadwal()
                onDeleteClick()
            }
        )
    }
}

@Composable
fun BodyDetailJadwal(
    modifier: Modifier = Modifier,
    detailUiState: DetailUiState = DetailUiState(),
    onDeleteClick: () -> Unit = { }
){
    var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }
    when {
        detailUiState.isLoading -> {
            Box(
                modifier = modifier.fillMaxSize().padding(50.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        detailUiState.isUiEventNotEmpty -> {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top
            ) {
                ItemDetailJadwal(
                    jadwal = detailUiState.detailUiEvent.toJadwalEntity(),
                    modifier = modifier.padding(bottom = 2.dp, top = 2.dp)
                )
                Button(
                    onClick = {
                        deleteConfirmationRequired = true
                    },
                    modifier = modifier.fillMaxWidth().padding(top = 2.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF40E0D0))
                ) {
                        Text(
                            text = "Delete",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White
                        )
                }
                if (deleteConfirmationRequired) {
                    DeleteConfirmationDialog(
                        onDeleteConfirm = {
                            deleteConfirmationRequired = false
                            onDeleteClick()
                        },
                        onDeleteCancel = { deleteConfirmationRequired = false },
                        modifier = modifier.padding(8.dp)
                    )
                }
            }
        }

        detailUiState.isUiEventEmpty -> {
            Box(
                modifier = modifier.fillMaxWidth().padding(top = 50.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Data tidak ditemukan",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun ItemDetailJadwal(
    modifier: Modifier = Modifier,
    jadwal: Jadwal
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0x8034C5B8)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ComponentDetailJadwal(judul = "Id Jadwal", isinya = jadwal.idJadwal.toString())
            Spacer(modifier = Modifier.height(8.dp))
            ComponentDetailJadwal(judul = "Nama Dokter", isinya = jadwal.namaDokter)
            Spacer(modifier = Modifier.height(8.dp))
            ComponentDetailJadwal(judul = "Nama Pasien", isinya = jadwal.namaPasien)
            Spacer(modifier = Modifier.height(8.dp))
            ComponentDetailJadwal(judul = "No Hp", isinya = jadwal.noHpPasien)
            Spacer(modifier = Modifier.height(8.dp))
            ComponentDetailJadwal(judul = "Tanggal Konsultasi", isinya = jadwal.tanggalKonsultasi)
            Spacer(modifier = Modifier.height(8.dp))
            ComponentDetailJadwal(judul = "Status", isinya = jadwal.status)
        }
    }
}

@Composable
fun ComponentDetailJadwal(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "$judul : ",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Gray
        )
        Text(
            text = isinya,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit, onDeleteCancel: () -> Unit, modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = { },
        title = { Text("Delete Data") },
        text = { Text("Apakah anda yakin ingin menghapus data?") },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(text = "Cancel")
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = "Yes")
            }
        }
    )
}