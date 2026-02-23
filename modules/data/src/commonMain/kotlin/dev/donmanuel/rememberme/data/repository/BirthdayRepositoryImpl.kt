package dev.donmanuel.rememberme.data.repository

import dev.donmanuel.rememberme.data.remote.RememberMeRemoteDataSource
import dev.donmanuel.rememberme.domain.model.Birthday
import dev.donmanuel.rememberme.domain.repository.BirthdayRepository

class BirthdayRepositoryImpl(
    private val remoteDataSource: RememberMeRemoteDataSource
) : BirthdayRepository {
    override suspend fun listBirthdays(baseUrl: String, apiKey: String): List<Birthday> {
        return remoteDataSource
            .listBirthdays(baseUrl = baseUrl, apiKey = apiKey)
            .map { dto ->
                Birthday(
                    id = dto.id,
                    fullName = dto.fullName,
                    dateOfBirth = dto.dateOfBirth,
                    nextBirthdayDate = dto.nextBirthdayDate,
                    daysUntilBirthday = dto.daysUntilBirthday,
                    nextBirthdayYearType = dto.nextBirthdayYearType,
                    photoPath = dto.photoPath
                )
            }
    }
}
