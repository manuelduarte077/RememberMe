package dev.donmanuel.rememberme.ui.navigation

data class AppDestination(
    val route: AppRoute,
    val label: String,
    val title: String,
) {
    companion object {
        val birthdays = AppDestination(
            route = AppRoute.Birthdays,
            label = "Cumpleaños",
            title = "RememberMe"
        )

        val addBirthday = AppDestination(
            route = AppRoute.AddBirthday,
            label = "Nuevo",
            title = "Agregar cumpleaños"
        )

        val about = AppDestination(
            route = AppRoute.About,
            label = "Acerca de",
            title = "Acerca de la app"
        )

        val birthdayDetail = AppDestination(
            route = AppRoute.BirthdayDetail("detail"),
            label = "Detalle",
            title = "Detalle del cumpleaños"
        )

        val entries: List<AppDestination> = listOf(birthdays, addBirthday, about)

        fun fromRoute(route: AppRoute): AppDestination {
            return when (route) {
                is AppRoute.BirthdayDetail -> birthdayDetail
                else -> entries.firstOrNull { it.route == route } ?: birthdays
            }
        }
    }
}
