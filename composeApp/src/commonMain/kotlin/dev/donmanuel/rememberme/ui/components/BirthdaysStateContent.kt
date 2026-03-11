package dev.donmanuel.rememberme.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.donmanuel.rememberme.presentation.model.BirthdaysUiState

@Composable
fun BirthdaysStateContent(
    uiState: BirthdaysUiState,
    onBirthdaySelected: (String) -> Unit,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        BirthdaysUiState.Idle -> {
            StateMessageCard(
                modifier = modifier,
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Info,
                        contentDescription = null
                    )
                },
                title = "Todo listo para comenzar",
                message = "Configura tus credenciales y toca \"Cargar cumpleaños\"."
            )
        }

        BirthdaysUiState.Loading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    CircularProgressIndicator()
                    Text(
                        text = "Cargando cumpleaños...",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        is BirthdaysUiState.Error -> {
            StateMessageCard(
                modifier = modifier,
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Error,
                        contentDescription = null
                    )
                },
                title = "No fue posible cargar los datos",
                message = uiState.message,
                actionLabel = "Reintentar",
                onActionClick = onRetry
            )
        }

        is BirthdaysUiState.Success -> {
            if (uiState.birthdays.isEmpty()) {
                StateMessageCard(
                    modifier = modifier,
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = null
                        )
                    },
                    title = "No hay cumpleaños registrados",
                    message = "Tu backend respondió correctamente, pero no hay datos para mostrar."
                )
            } else {
                LazyColumn(
                    modifier = modifier.fillMaxSize(),
                    contentPadding = PaddingValues(vertical = 4.dp, horizontal = 1.dp),
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

@Composable
private fun StateMessageCard(
    modifier: Modifier,
    icon: @Composable () -> Unit,
    title: String,
    message: String,
    actionLabel: String? = null,
    onActionClick: (() -> Unit)? = null
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        ElevatedCard(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 18.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                icon()
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = message,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                if (actionLabel != null && onActionClick != null) {
                    TextButton(onClick = onActionClick) {
                        Text(actionLabel)
                    }
                }
            }
        }
    }
}

internal fun BirthdaysUiState.isLoading(): Boolean {
    return this is BirthdaysUiState.Loading
}
