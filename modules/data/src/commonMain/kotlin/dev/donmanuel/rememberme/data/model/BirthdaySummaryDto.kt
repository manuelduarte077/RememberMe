package dev.donmanuel.rememberme.data.model

data class BirthdaySummaryDto(
    val id: String,
    val fullName: String,
    val dateOfBirth: String,
    val nextBirthdayDate: String,
    val daysUntilBirthday: Int,
    val nextBirthdayYearType: String,
    val photoPath: String?
)
