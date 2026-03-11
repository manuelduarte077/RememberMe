package dev.donmanuel.rememberme.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.ElevatedCard
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
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .navigationBarsPadding()
            .imePadding(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if (birthday == null) {
            ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "No se encontró el cumpleaños solicitado.",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                )
            }
            return
        }

        Text(
            text = birthday.fullName,
            style = MaterialTheme.typography.headlineSmall
        )

        ElevatedCard(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("ID: ${birthday.id}", style = MaterialTheme.typography.bodyMedium)
                Text("Fecha de nacimiento: ${birthday.dateOfBirth}", style = MaterialTheme.typography.bodyMedium)
                Text("Próximo cumpleaños: ${birthday.nextBirthdayDate}", style = MaterialTheme.typography.bodyMedium)
                Text("Tipo de año: ${birthday.nextBirthdayYearType}", style = MaterialTheme.typography.bodyMedium)
                Text(
                    "Días restantes: ${birthday.daysUntilBirthday}",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}
