package dev.donmanuel.rememberme.domain.repository

import dev.donmanuel.rememberme.domain.model.Birthday

interface BirthdayRepository {
    suspend fun listBirthdays(baseUrl: String, apiKey: String): List<Birthday>
}
