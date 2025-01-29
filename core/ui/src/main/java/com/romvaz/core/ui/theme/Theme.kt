package com.romvaz.core.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

val LocalDevTekColors = staticCompositionLocalOf {
    DevTekColors()
}

private val DarkColorScheme = darkColorScheme(
    primary = Colors.Primary100,
    onPrimary = Colors.Black,
    secondary = Colors.Secondary60,
    surface = Colors.Black,
    surfaceVariant = Colors.Gray30,
    onSurface = Colors.White,
    inverseSurface = Colors.White,
    inverseOnSurface = Colors.Gray10,
    onSurfaceVariant = Colors.White,
    secondaryContainer = Colors.Primary80,
    onSecondaryContainer = Colors.Black
)

val LightColorScheme = lightColorScheme(
    primary = Colors.Primary100,
    onPrimary = Colors.White,
    secondary = Colors.Secondary60,
    surface = Colors.White,
    surfaceVariant = Colors.Gray90,
    onSurface = Colors.Black,
    inverseSurface = Colors.Black,
    inverseOnSurface = Colors.White,
    onSurfaceVariant = Colors.Primary60,
    secondaryContainer = Colors.Gray10,
    onSecondaryContainer = Colors.Primary100
)

@Immutable
data class DarkMode(val isDarkMode: Boolean)

val LocalIsDarkMode = staticCompositionLocalOf {
    DarkMode(isDarkMode = false)
}

@Composable
fun DevTekTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val devTekColors = DevTekColors()

    val darkMode = DarkMode(darkTheme)
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    SideEffect {
        val window = (view.context as Activity).window
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
    }

    CompositionLocalProvider(
        LocalIsDarkMode provides darkMode,
        LocalDevTekColors provides devTekColors
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content,
            shapes = Shapes()
        )
    }
}

private val darkMode: DarkMode
    @Composable
    get() = LocalIsDarkMode.current

@Composable
fun isDarkTheme() = darkMode.isDarkMode

val MaterialTheme.devTekColors
    @Composable
    get() = LocalDevTekColors.current
