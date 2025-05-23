package com.tuusuario.ucbmobile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.google.maps.android.compose.*
import androidx.compose.ui.Alignment
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState


@Composable
fun SimRequestForm(
    onBack: () -> Unit,
    onSubmit: (phone: String, latitude: Double, longitude: Double) -> Unit
) {
    var phone by remember { mutableStateOf("") }
    var selectedLocation by remember { mutableStateOf<LatLng?>(null) }

    Dialog(onDismissRequest = onBack) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Opción donde enviaremos su SIM",
                    style = MaterialTheme.typography.headlineSmall
                )

                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("Teléfono de referencia") },
                    modifier = Modifier.fillMaxWidth()
                )

                // Mapa
                Text("Seleccione su ubicación en el mapa:")
                val cameraPositionState = rememberCameraPositionState {
                    position = CameraPosition.fromLatLngZoom(LatLng(-16.5, -68.15), 12f)
                }

                GoogleMap(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    cameraPositionState = cameraPositionState,
                    onMapClick = { latLng ->
                        selectedLocation = latLng
                    }
                ) {
                    selectedLocation?.let {
                        Marker(
                            state = MarkerState(position = it),
                            title = "Ubicación seleccionada"
                        )
                    }
                }

                // Mostrar lat/lng si se seleccionó
                if (selectedLocation != null) {
                    Text("Latitud: ${selectedLocation!!.latitude}")
                    Text("Longitud: ${selectedLocation!!.longitude}")
                }

                // Botones
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onBack) {
                        Text("Cancelar")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {
                            selectedLocation?.let {
                                onSubmit(phone, it.latitude, it.longitude)
                            }
                        },
                        enabled = phone.isNotBlank() && selectedLocation != null
                    ) {
                        Text("Continuar")
                    }
                }
            }
        }
    }
}
