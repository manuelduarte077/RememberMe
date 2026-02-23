package dev.donmanuel.rememberme.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.donmanuel.rememberme.presentation.model.BirthdayItemUiModel

@Composable
fun BirthdayDetailScreen(birthday: BirthdayItemUiModel?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        if (birthday == null) {
            Text(
                text = "No se encontró el cumpleaños solicitado.",
                style = MaterialTheme.typography.bodyLarge
            )
            return
        }

        Text(
            text = birthday.fullName,
            style = MaterialTheme.typography.headlineSmall
        )
        Text("ID: ${birthday.id}")
        Text("Fecha de nacimiento: ${birthday.dateOfBirth}")
        Text("Próximo cumpleaños: ${birthday.nextBirthdayDate}")
        Text("Tipo de año: ${birthday.nextBirthdayYearType}")
        Text("Días restantes: ${birthday.daysUntilBirthday}")
    }
}
