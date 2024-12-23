package com.example.ucp3.ui.View.Jadwal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp3.data.entity.Dokter
import com.example.ucp3.ui.costumewidget.TopAppBar
import com.example.ucp3.ui.navigation.AlamatNavigasi
import com.example.ucp3.ui.viewmodel.JadwalEvent
import com.example.ucp3.ui.viewmodel.JadwalUIState
import com.example.ucp3.ui.viewmodel.JadwalViewModel
import com.example.ucp3.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch

object DestinasiInsertJadwal : AlamatNavigasi {
    override val route = "insert_jadwal"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun InsertJadwalView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: JadwalViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.uiState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(uiState.snackbarMessage) {
        uiState.snackbarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.resetSnackBarMessage()
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "Tambah Jadwal",
                modifier = Modifier.padding(50.dp)
            )
        },
        modifier = Modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            InsertBodyJadwal(
                uiState = uiState,
                onValueChange = { updateEvent ->
                    viewModel.updateState(updateEvent)
                },
                onClick = {
                    coroutineScope.launch {
                        viewModel.saveData()
                    }
                    onNavigate()
                }
            )
        }
    }
}


@Composable
fun InsertBodyJadwal(
    modifier: Modifier = Modifier,
    onValueChange: (JadwalEvent) -> Unit,
    uiState: JadwalUIState,
    onClick: () -> Unit,
    ListNamaDokter: List<Dokter> = uiState.dokterList
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Input Jadwal",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.Unspecified,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        InputField(
            label = "Nama Pasien",
            value = uiState.jadwalEvent.namaPasien,
            error = uiState.isEntryValid.namaPasien,
            onValueChange = { onValueChange(uiState.jadwalEvent.copy(namaPasien = it)) }
        )

        Pilihan(
            selectedNama = uiState.jadwalEvent.namaDokter,
            onSelect = { selectedDokter ->
                onValueChange(uiState.jadwalEvent.copy(namaDokter = selectedDokter))
            },
            dokterList = ListNamaDokter
        )

        InputField(
            label = "No Hp",
            value = uiState.jadwalEvent.noHpPasien,
            error = uiState.isEntryValid.noHpPasien,
            onValueChange = { onValueChange(uiState.jadwalEvent.copy(noHpPasien = it)) }
        )

        InputField(
            label = "Tanggal Konsultasi",
            value = uiState.jadwalEvent.tanggalKonsultasi,
            error = uiState.isEntryValid.tanggalKonsultasi,
            onValueChange = { onValueChange(uiState.jadwalEvent.copy(tanggalKonsultasi = it)) }
        )

        InputField(
            label = "Status",
            value = uiState.jadwalEvent.status,
            error = uiState.isEntryValid.status,
            onValueChange = { onValueChange(uiState.jadwalEvent.copy(status = it)) }
        )

        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF40E0D0)
            )
        ) {
            Text(
                text = "Simpan",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
        }
    }
}

@Composable
fun InputField(
    label: String,
    value: String,
    error: String?,
    onValueChange: (String) -> Unit
) {
    Column {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            isError = error != null,
            singleLine = true
        )
        if (error != null) {
            Text(
                text = error,
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Pilihan(
    selectedNama: String?,
    onSelect: (String) -> Unit,
    dokterList: List<Dokter>
) {
    val options = dokterList.map { it.nama}
    val expanded = rememberSaveable { mutableStateOf(false) }
    val currentSelection = rememberSaveable { mutableStateOf(selectedNama ?: options[0]) }

    ExposedDropdownMenuBox(
        expanded = expanded.value,
        onExpandedChange = { expanded.value = !expanded.value }
    ) {
        OutlinedTextField(
            value = currentSelection.value,
            onValueChange = {},
            readOnly = true,
            label = { Text("Nama Dokter") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded.value) },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(selectionOption) },
                    onClick = {
                        currentSelection.value = selectionOption
                        expanded.value = false
                        onSelect(selectionOption)
                    }
                )
            }
        }
    }
}

