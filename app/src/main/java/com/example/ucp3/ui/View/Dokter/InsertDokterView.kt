package com.example.ucp3.ui.View.Dokter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp3.ui.costumewidget.TopAppBar
import com.example.ucp3.ui.navigation.AlamatNavigasi
import com.example.ucp3.ui.viewmodel.DokterEvent
import com.example.ucp3.ui.viewmodel.DokterViewModel
import com.example.ucp3.ui.viewmodel.DokteruiState
import com.example.ucp3.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch

object DestinasiInsertDokter : AlamatNavigasi {
    override val route = "insert_dok"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun InsertDokterView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DokterViewModel = viewModel(factory = PenyediaViewModel.Factory)
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
                judul = "Tambah Dokter"
            )
        },
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp)
                .padding(padding)
        ) {
            InsertBodyDokter(
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
fun InsertBodyDokter(
    modifier: Modifier = Modifier,
    onValueChange: (DokterEvent) -> Unit,
    uiState: DokteruiState,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(
                color = Color.White,
                shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
            )
            .fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Input Data Dokter",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Unspecified,
                modifier = Modifier.align(Alignment.CenterHorizontally)

            )

            InputField(
                label = "Nama",
                value = uiState.dokterEvent.nama,
                error = uiState.isEntryValid.nama,
                onValueChange = { onValueChange(uiState.dokterEvent.copy(nama = it)) }
            )

            Pilihan(
                selectedSpesialis = uiState.dokterEvent.spesialis,
                onSelect = { onValueChange(uiState.dokterEvent.copy(spesialis = it)) }
            )

            InputField(
                label = "Klinik",
                value = uiState.dokterEvent.klinik,
                error = uiState.isEntryValid.klinik,
                onValueChange = { onValueChange(uiState.dokterEvent.copy(klinik = it)) }
            )

            InputField(
                label = "No Hp",
                value = uiState.dokterEvent.noHpDokter,
                error = uiState.isEntryValid.noHpDokter,
                onValueChange = { onValueChange(uiState.dokterEvent.copy(noHpDokter = it)) }
            )

            InputField(
                label = "Jam Kerja",
                value = uiState.dokterEvent.jamKerja,
                error = uiState.isEntryValid.jamKerja,
                onValueChange = { onValueChange(uiState.dokterEvent.copy(jamKerja = it)) }
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
    selectedSpesialis: String?,
    onSelect: (String) -> Unit
) {
    val options = listOf("Dokter Umum", "Dokter Bedah", "Dokter Saraf dan Neurologis", "Dokter Penyakit Dalam", "Dokter Gigi")
    val expanded = rememberSaveable { mutableStateOf(false) }
    val currentSelection = rememberSaveable { mutableStateOf(selectedSpesialis ?: options[0]) }

    ExposedDropdownMenuBox(
        expanded = expanded.value,
        onExpandedChange = { expanded.value = !expanded.value }
    ) {
        OutlinedTextField(
            value = currentSelection.value,
            onValueChange = {},
            readOnly = true,
            label = { Text("Spesialis") },
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