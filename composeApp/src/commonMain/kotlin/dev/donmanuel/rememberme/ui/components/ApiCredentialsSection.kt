package dev.donmanuel.rememberme.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

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
    var apiKeyVisible by rememberSaveable { mutableStateOf(false) }

    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Conexión con backend",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Ingresa la URL base y tu API key para cargar los cumpleaños.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            OutlinedTextField(
                value = baseUrl,
                onValueChange = onBaseUrlChanged,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Base URL API") },
                singleLine = true,
                enabled = !isLoading,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Uri,
                    imeAction = ImeAction.Next
                )
            )

            OutlinedTextField(
                value = apiKey,
                onValueChange = onApiKeyChanged,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("X-API-Key") },
                singleLine = true,
                enabled = !isLoading,
                visualTransformation = if (apiKeyVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                trailingIcon = {
                    IconButton(onClick = { apiKeyVisible = !apiKeyVisible }) {
                        Icon(
                            imageVector = if (apiKeyVisible) {
                                Icons.Filled.VisibilityOff
                            } else {
                                Icons.Filled.Visibility
                            },
                            contentDescription = if (apiKeyVisible) {
                                "Ocultar API key"
                            } else {
                                "Mostrar API key"
                            }
                        )
                    }
                }
            )

            Button(
                onClick = onLoadBirthdays,
                modifier = Modifier.fillMaxWidth(),
                enabled = canLoad && !isLoading
            ) {
                if (isLoading) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(18.dp),
                            strokeWidth = 2.dp
                        )
                        Text("Cargando cumpleaños...")
                    }
                } else {
                    Text("Cargar cumpleaños")
                }
            }
        }
    }
}
