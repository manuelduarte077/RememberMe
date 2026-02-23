package dev.donmanuel.rememberme.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import dev.donmanuel.rememberme.presentation.model.BirthdayItemUiModel
import dev.donmanuel.rememberme.presentation.model.BirthdaysUiState
import dev.donmanuel.rememberme.presentation.model.BirthdaysViewState
import dev.donmanuel.rememberme.ui.screens.AboutScreen
import dev.donmanuel.rememberme.ui.screens.AddBirthdayScreen
import dev.donmanuel.rememberme.ui.screens.BirthdayDetailScreen
import dev.donmanuel.rememberme.ui.screens.BirthdaysScreen
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun RememberMeNavigationScaffold(
    viewState: BirthdaysViewState,
    onBaseUrlChanged: (String) -> Unit,
    onApiKeyChanged: (String) -> Unit,
    onLoadBirthdays: () -> Unit
) {
    val savedStateConfiguration = remember {
        SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class) {
                    subclass(AppRoute.Birthdays.serializer())
                    subclass(AppRoute.AddBirthday.serializer())
                    subclass(AppRoute.About.serializer())
                    subclass(AppRoute.BirthdayDetail.serializer())
                }
            }
        }
    }
    val backStack = rememberNavBackStack(
        savedStateConfiguration,
        AppRoute.Birthdays
    )
    val currentRoute = (backStack.lastOrNull() as? AppRoute) ?: AppRoute.Birthdays
    val currentDestination = AppDestination.fromRoute(currentRoute)
    val showBottomBar = currentRoute !is AppRoute.BirthdayDetail
    val navEntries = entryProvider<NavKey> {
        entry(AppRoute.Birthdays) {
            BirthdaysScreen(
                viewState = viewState,
                onBaseUrlChanged = onBaseUrlChanged,
                onApiKeyChanged = onApiKeyChanged,
                onLoadBirthdays = onLoadBirthdays,
                onBirthdaySelected = { birthdayId ->
                    backStack.add(AppRoute.BirthdayDetail(birthdayId))
                }
            )
        }
        entry(AppRoute.AddBirthday) {
            AddBirthdayScreen()
        }
        entry(AppRoute.About) {
            AboutScreen()
        }
        entry<AppRoute.BirthdayDetail> { route ->
            BirthdayDetailScreen(
                birthday = viewState.findBirthdayById(route.birthdayId)
            )
        }
    }

    Scaffold(
        topBar = {
            MediumFlexibleTopAppBar(
                title = {
                    Text(
                        currentDestination.title,
                    )
                },
                navigationIcon = {
                    if (currentRoute is AppRoute.BirthdayDetail) {
                        IconButton(
                            onClick = {
                                if (backStack.size > 1) {
                                    backStack.removeAt(backStack.lastIndex)
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Volver"
                            )
                        }
                    }
                }
            )
        },
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    AppDestination.entries.forEach { destination ->
                        NavigationBarItem(
                            selected = destination.route == currentDestination.route,
                            onClick = {
                                if (destination.route != currentRoute) {
                                    backStack.clear()
                                    backStack.add(destination.route)
                                }
                            },
                            label = { Text(destination.label) },
                            icon = {
                                Icon(
                                    imageVector = destination.icon(),
                                    contentDescription = destination.label
                                )
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            NavDisplay(
                backStack = backStack,
                onBack = {
                    if (backStack.size > 1) {
                        backStack.removeAt(backStack.lastIndex)
                    }
                },
                entryProvider = navEntries
            )
        }
    }
}

private fun BirthdaysViewState.findBirthdayById(id: String): BirthdayItemUiModel? {
    val successState = uiState as? BirthdaysUiState.Success ?: return null
    return successState.birthdays.firstOrNull { birthday -> birthday.id == id }
}

private fun AppDestination.icon(): ImageVector {
    return when (route) {
        AppRoute.Birthdays -> Icons.Filled.Cake
        AppRoute.AddBirthday -> Icons.Filled.Add
        AppRoute.About -> Icons.Filled.Info
        is AppRoute.BirthdayDetail -> Icons.Filled.Cake
    }
}
