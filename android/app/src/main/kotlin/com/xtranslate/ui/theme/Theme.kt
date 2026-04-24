package com.xtranslate.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
  primary = Color(0xFFA78BFA),
  secondary = Color(0xFF4DD9FF),
  tertiary = Color(0xFFFFF44F),
  background = Color(0xFF0F172A),
  surface = Color(0xFF1E293B),
  error = Color(0xFFE63946)
)

private val LightColorScheme = lightColorScheme(
  primary = Color(0xFF6C5CE7),
  secondary = Color(0xFF00B4D8),
  tertiary = Color(0xFFFFD60A),
  background = Color(0xFFFFFFFF),
  surface = Color(0xFFF8F9FA),
  error = Color(0xFFE63946)
)

@Composable
fun XTranslateTheme(
  darkTheme: Boolean = false,
  content: @Composable () -> Unit
) {
  val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

  MaterialTheme(
    colorScheme = colorScheme,
    typography = XTranslateTypography,
    content = content
  )
}
