package dev.donmanuel.rememberme.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.donmanuel.rememberme.presentation.model.BirthdaysViewState
import dev.donmanuel.rememberme.ui.components.ApiCredentialsSection
import dev.donmanuel.rememberme.ui.components.BirthdaysStateContent
import dev.donmanuel.rememberme.ui.components.isLoading

@Composable
fun BirthdaysScreen(
    viewState: BirthdaysViewState,
    onBaseUrlChanged: (String) -> Unit,
    onApiKeyChanged: (String) -> Unit,
    onLoadBirthdays: () -> Unit,
    onBirthdaySelected: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ApiCredentialsSection(
            baseUrl = viewState.baseUrl,
            apiKey = viewState.apiKey,
            canLoad = viewState.canLoad,
            isLoading = viewState.uiState.isLoading(),
            onBaseUrlChanged = onBaseUrlChanged,
            onApiKeyChanged = onApiKeyChanged,
            onLoadBirthdays = onLoadBirthdays
        )

        BirthdaysStateContent(
            uiState = viewState.uiState,
            onBirthdaySelected = onBirthdaySelected
        )
    }
}
