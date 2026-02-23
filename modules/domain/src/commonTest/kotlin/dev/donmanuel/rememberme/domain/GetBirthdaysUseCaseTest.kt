package dev.donmanuel.rememberme.domain

import dev.donmanuel.rememberme.domain.model.Birthday
import dev.donmanuel.rememberme.domain.repository.BirthdayRepository
import dev.donmanuel.rememberme.domain.usecase.GetBirthdaysUseCase
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class GetBirthdaysUseCaseTest {

    @Test
    fun `throws when base url is blank`() {
        val useCase = GetBirthdaysUseCase(FakeBirthdayRepository())

        val exception = assertFailsWith<IllegalArgumentException> {
            runBlocking { useCase(baseUrl = " ", apiKey = "abc") }
        }

        assertEquals("La base URL es obligatoria.", exception.message)
    }

    @Test
    fun `throws when api key is blank`() {
        val useCase = GetBirthdaysUseCase(FakeBirthdayRepository())

        val exception = assertFailsWith<IllegalArgumentException> {
            runBlocking { useCase(baseUrl = "https://rememberme.donmanuel.dev", apiKey = " ") }
        }

        assertEquals("La API key es obligatoria.", exception.message)
    }

    @Test
    fun `returns birthdays from repository`() {
        val expected = listOf(
            Birthday(
                id = "1",
                fullName = "John Doe",
                dateOfBirth = "1990-01-01",
                nextBirthdayDate = "2026-01-01",
                daysUntilBirthday = 10,
                nextBirthdayYearType = "Normal",
                photoPath = null
            )
        )
        val useCase = GetBirthdaysUseCase(FakeBirthdayRepository(expected))

        val result = runBlocking {
            useCase(baseUrl = "https://rememberme.donmanuel.dev", apiKey = "abc")
        }

        assertEquals(expected, result)
    }
}

private class FakeBirthdayRepository(
    private val data: List<Birthday> = emptyList()
) : BirthdayRepository {
    override suspend fun listBirthdays(baseUrl: String, apiKey: String): List<Birthday> {
        return data
    }
}
