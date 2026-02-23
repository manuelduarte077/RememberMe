package dev.donmanuel.rememberme.presentation.viewmodel

import dev.donmanuel.rememberme.domain.model.Birthday
import dev.donmanuel.rememberme.domain.usecase.GetBirthdaysUseCase
import dev.donmanuel.rememberme.presentation.model.BirthdayItemUiModel
import dev.donmanuel.rememberme.presentation.model.BirthdaysUiState
import dev.donmanuel.rememberme.presentation.model.BirthdaysViewState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BirthdaysViewModel(
    private val getBirthdaysUseCase: GetBirthdaysUseCase,
    dispatcher: CoroutineDispatcher = Dispatchers.Default
) {
    private val scope = CoroutineScope(SupervisorJob() + dispatcher)
    private val mutableViewState = MutableStateFlow(
        BirthdaysViewState(
            baseUrl = defaultBaseUrl,
            apiKey = ""
        )
    )
    val viewState: StateFlow<BirthdaysViewState> = mutableViewState.asStateFlow()

    fun onBaseUrlChanged(value: String) {
        mutableViewState.update { current -> current.copy(baseUrl = value) }
    }

    fun onApiKeyChanged(value: String) {
        mutableViewState.update { current -> current.copy(apiKey = value) }
    }

    fun loadBirthdays() {
        val currentState = mutableViewState.value
        if (currentState.baseUrl.isBlank() || currentState.apiKey.isBlank()) {
            mutableViewState.update { state ->
                state.copy(uiState = BirthdaysUiState.Error("La base URL y API key son obligatorias."))
            }
            return
        }

        scope.launch {
            mutableViewState.update { state -> state.copy(uiState = BirthdaysUiState.Loading) }

            val nextUiState = runCatching {
                getBirthdaysUseCase(
                    baseUrl = currentState.baseUrl,
                    apiKey = currentState.apiKey
                )
            }.fold(
                onSuccess = { birthdays ->
                    BirthdaysUiState.Success(
                        birthdays = birthdays.map { birthday -> birthday.toUiModel() }
                    )
                },
                onFailure = { error ->
                    BirthdaysUiState.Error(
                        message = error.message ?: "No fue posible cargar cumpleaños."
                    )
                }
            )

            mutableViewState.update { state -> state.copy(uiState = nextUiState) }
        }
    }

    fun clear() {
        scope.cancel()
    }
}

private const val defaultBaseUrl = "https://rememberme.donmanuel.dev"

private fun Birthday.toUiModel(): BirthdayItemUiModel {
    return BirthdayItemUiModel(
        id = id,
        fullName = fullName,
        dateOfBirth = dateOfBirth,
        nextBirthdayDate = nextBirthdayDate,
        daysUntilBirthday = daysUntilBirthday,
        nextBirthdayYearType = nextBirthdayYearType,
        photoPath = photoPath
    )
}
