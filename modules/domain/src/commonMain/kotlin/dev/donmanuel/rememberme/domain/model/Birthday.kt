package dev.donmanuel.rememberme.domain.model

data class Birthday(
    val id: String,
    val fullName: String,
    val dateOfBirth: String,
    val nextBirthdayDate: String,
    val daysUntilBirthday: Int,
    val nextBirthdayYearType: String,
    val photoPath: String?
)
