package dev.donmanuel.rememberme.presentation.model

data class BirthdaysViewState(
    val baseUrl: String,
    val apiKey: String,
    val uiState: BirthdaysUiState = BirthdaysUiState.Idle
) {
    val canLoad: Boolean
        get() = uiState !is BirthdaysUiState.Loading &&
                baseUrl.isNotBlank() &&
                apiKey.isNotBlank()
}
