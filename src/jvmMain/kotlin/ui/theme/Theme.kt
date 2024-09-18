package ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = GrayTeal2,
    primaryVariant = GrayTeal4,
    secondary = GrayTeal3,
    surface = GrayTeal3,
    onSurface = Color.White,
    onPrimary = Color.White
)

private val LightColorPalette = lightColors(
    primary = GrayTeal1,
    primaryVariant = GrayTeal3,
    secondary = GrayTeal2,
    surface = GrayTeal4,
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = LightColorPalette,
        content = content
    )
}
