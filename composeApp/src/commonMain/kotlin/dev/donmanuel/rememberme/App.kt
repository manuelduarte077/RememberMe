package dev.donmanuel.rememberme

import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import dev.donmanuel.rememberme.di.AppDi
import dev.donmanuel.rememberme.ui.navigation.RememberMeNavigationScaffold
import dev.donmanuel.rememberme.ui.theme.RememberMeTheme

@Composable
@Preview
fun App() {
    val viewModel = remember {
        AppDi.start()
        AppDi.birthdaysViewModel()
    }
    val viewState by viewModel.viewState.collectAsState()

    DisposableEffect(Unit) {
        onDispose {
            viewModel.clear()
            AppDi.stop()
        }
    }

    RememberMeTheme {
        RememberMeNavigationScaffold(
            viewState = viewState,
            onBaseUrlChanged = viewModel::onBaseUrlChanged,
            onApiKeyChanged = viewModel::onApiKeyChanged,
            onLoadBirthdays = viewModel::loadBirthdays
        )
    }
}
