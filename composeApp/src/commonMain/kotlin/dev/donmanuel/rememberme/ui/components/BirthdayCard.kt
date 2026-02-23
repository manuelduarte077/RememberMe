package dev.donmanuel.rememberme.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.donmanuel.rememberme.presentation.model.BirthdayItemUiModel

@Composable
fun BirthdayCard(
    item: BirthdayItemUiModel,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(item.fullName, style = MaterialTheme.typography.titleMedium)
            Text("Nace: ${item.dateOfBirth}")
            Text("Próximo: ${item.nextBirthdayDate} (${item.nextBirthdayYearType})")
            Text("Faltan: ${item.daysUntilBirthday} días")
        }
    }
}
