package dev.donmanuel.rememberme.domain.usecase

import dev.donmanuel.rememberme.domain.model.Birthday
import dev.donmanuel.rememberme.domain.repository.BirthdayRepository

class GetBirthdaysUseCase(
    private val repository: BirthdayRepository
) {
    suspend operator fun invoke(baseUrl: String, apiKey: String): List<Birthday> {
        val normalizedBaseUrl = baseUrl.trim()
        if (normalizedBaseUrl.isBlank()) {
            throw IllegalArgumentException("La base URL es obligatoria.")
        }

        val normalizedApiKey = apiKey.trim()
        if (normalizedApiKey.isBlank()) {
            throw IllegalArgumentException("La API key es obligatoria.")
        }

        return repository.listBirthdays(
            baseUrl = normalizedBaseUrl,
            apiKey = normalizedApiKey
        )
    }
}
