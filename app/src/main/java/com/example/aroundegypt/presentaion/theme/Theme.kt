package com.example.aroundegypt.presentaion.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White

private val LightColorScheme = lightColorScheme(
    primary = Accent,
    background = White,
    onBackground = Black,
    surface = White,
    onSurface = Black,
    onSurfaceVariant = Gray,
    onPrimary = Accent
)

@Composable
fun AroundEgyptTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = AppTypography,
        content = content
    )
}