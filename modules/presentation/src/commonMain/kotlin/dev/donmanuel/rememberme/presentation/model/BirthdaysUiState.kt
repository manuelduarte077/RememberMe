package dev.donmanuel.rememberme.presentation.model

sealed interface BirthdaysUiState {
    data object Idle : BirthdaysUiState
    data object Loading : BirthdaysUiState
    data class Success(val birthdays: List<BirthdayItemUiModel>) : BirthdaysUiState
    data class Error(val message: String) : BirthdaysUiState
}
