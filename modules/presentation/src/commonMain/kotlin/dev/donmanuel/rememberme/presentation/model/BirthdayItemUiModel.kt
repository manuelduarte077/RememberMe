package dev.donmanuel.rememberme.presentation.model

data class BirthdayItemUiModel(
    val id: String,
    val fullName: String,
    val dateOfBirth: String,
    val nextBirthdayDate: String,
    val daysUntilBirthday: Int,
    val nextBirthdayYearType: String,
    val photoPath: String?
)
