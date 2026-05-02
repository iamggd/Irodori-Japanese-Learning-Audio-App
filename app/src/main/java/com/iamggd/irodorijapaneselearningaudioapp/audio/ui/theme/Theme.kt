package com.iamggd.irodorijapaneselearningaudioapp.audio.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val LightColors = lightColorScheme(
    background = Color(0xFFF8F4EF), // warm off-white
    surface = Color(0xFFFFFFFF),
    primary = Color(0xFFE05C2A), // Irodori orange
    secondary = Color(0xFF4A90D9), // calm blue
    onBackground = Color(0xFF1A1A1A), // Text Primary
    onSurfaceVariant = Color(0xFF666666) // Text Secondary
)

val DarkColors = darkColorScheme(
    background = Color(0xFF121212), // material dark
    surface = Color(0xFF1E1E1E),
    primary = Color(0xFFFF7043),
    secondary = Color(0xFF64B5F6),
    onBackground = Color(0xFFF0F0F0),
    onSurfaceVariant = Color(0xFFAAAAAA)
)

@Composable
fun IrodoriTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}