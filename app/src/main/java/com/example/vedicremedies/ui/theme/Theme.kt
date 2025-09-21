package com.example.vedicremedies.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = DeepSaffron,
    onPrimary = MoonWhite,
    primaryContainer = LightSaffron,
    onPrimaryContainer = Midnight,
    secondary = Saffron,
    onSecondary = Midnight,
    secondaryContainer = Sand,
    onSecondaryContainer = Midnight,
    tertiary = WisdomGold,
    onTertiary = Midnight,
    background = MoonWhite,
    onBackground = Midnight,
    surface = Sand,
    onSurface = Midnight,
    surfaceVariant = LightSaffron,
    onSurfaceVariant = Midnight,
    outline = DeepSaffron.copy(alpha = 0.5f)
)

private val DarkColorScheme = darkColorScheme(
    primary = Saffron,
    onPrimary = Midnight,
    primaryContainer = DeepSaffron,
    onPrimaryContainer = MoonWhite,
    secondary = LightSaffron,
    onSecondary = Midnight,
    secondaryContainer = DeepMidnight,
    onSecondaryContainer = Sand,
    tertiary = WisdomGold,
    onTertiary = Midnight,
    background = DeepMidnight,
    onBackground = MoonWhite,
    surface = Midnight,
    onSurface = Sand,
    surfaceVariant = DeepMidnight,
    onSurfaceVariant = Sand,
    outline = Saffron.copy(alpha = 0.5f)
)

@Composable
fun VedicRemediesTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (useDarkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}
