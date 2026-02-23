package dev.donmanuel.rememberme.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AboutScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "RememberMe",
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = "App mobile multiplataforma para consumir la API de cumpleaños.",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Configura Base URL + API key, carga datos y valida integración Android/iOS.",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
