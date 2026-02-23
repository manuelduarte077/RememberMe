package dev.donmanuel.rememberme.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.donmanuel.rememberme.presentation.model.BirthdaysUiState

@Composable
fun BirthdaysStateContent(
    uiState: BirthdaysUiState,
    onBirthdaySelected: (String) -> Unit
) {
    when (uiState) {
        BirthdaysUiState.Idle -> {
            Text("Ingresa la URL y API key para consultar tu backend.")
        }

        BirthdaysUiState.Loading -> {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }

        is BirthdaysUiState.Error -> {
            Text(
                text = uiState.message,
                color = Color(0xFFB00020)
            )
        }

        is BirthdaysUiState.Success -> {
            if (uiState.birthdays.isEmpty()) {
                Text("No hay cumpleaños registrados.")
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(uiState.birthdays, key = { it.id }) { birthday ->
                        BirthdayCard(
                            item = birthday,
                            onClick = { onBirthdaySelected(birthday.id) }
                        )
                    }
                }
            }
        }
    }
}

internal fun BirthdaysUiState.isLoading(): Boolean {
    return this is BirthdaysUiState.Loading
}
