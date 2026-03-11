package dev.donmanuel.rememberme.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF0F5C91),
    onPrimary = Color(0xFFFFFFFF),
    secondary = Color(0xFF2A6B55),
    onSecondary = Color(0xFFFFFFFF),
    surface = Color(0xFFF7F9FC),
    onSurface = Color(0xFF1A1C1F),
    surfaceVariant = Color(0xFFE4E8EF),
    onSurfaceVariant = Color(0xFF43474E),
    error = Color(0xFFBA1A1A),
    onError = Color(0xFFFFFFFF)
)

private val BaseTypography = Typography()

private val AppTypography = BaseTypography.copy(
    headlineSmall = BaseTypography.headlineSmall.copy(fontWeight = FontWeight.SemiBold),
    titleLarge = BaseTypography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
    titleMedium = BaseTypography.titleMedium.copy(fontWeight = FontWeight.Medium),
    bodyLarge = BaseTypography.bodyLarge.copy(lineHeight = BaseTypography.bodyLarge.lineHeight),
    bodyMedium = BaseTypography.bodyMedium.copy(lineHeight = BaseTypography.bodyMedium.lineHeight)
)

@Composable
fun RememberMeTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = AppTypography,
        content = content
    )
}
