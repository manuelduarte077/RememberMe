package dev.donmanuel.rememberme.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ApiCredentialsSection(
    baseUrl: String,
    apiKey: String,
    canLoad: Boolean,
    isLoading: Boolean,
    onBaseUrlChanged: (String) -> Unit,
    onApiKeyChanged: (String) -> Unit,
    onLoadBirthdays: () -> Unit
) {
    OutlinedTextField(
        value = baseUrl,
        onValueChange = onBaseUrlChanged,
        modifier = Modifier.fillMaxWidth(),
        label = { Text("Base URL API") },
        singleLine = true
    )

    OutlinedTextField(
        value = apiKey,
        onValueChange = onApiKeyChanged,
        modifier = Modifier.fillMaxWidth(),
        label = { Text("X-API-Key") },
        singleLine = true
    )

    Button(
        onClick = onLoadBirthdays,
        modifier = Modifier.fillMaxWidth(),
        enabled = canLoad
    ) {
        Text(if (isLoading) "Cargando..." else "Cargar cumpleaños")
    }
}
