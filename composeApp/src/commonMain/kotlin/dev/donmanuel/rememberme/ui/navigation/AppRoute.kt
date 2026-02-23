package dev.donmanuel.rememberme.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface AppRoute : NavKey {
    @Serializable
    data object Birthdays : AppRoute

    @Serializable
    data object AddBirthday : AppRoute

    @Serializable
    data object About : AppRoute

    @Serializable
    data class BirthdayDetail(val birthdayId: String) : AppRoute
}
