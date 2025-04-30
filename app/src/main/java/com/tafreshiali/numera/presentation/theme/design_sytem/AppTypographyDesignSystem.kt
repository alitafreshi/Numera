package com.tafreshiali.numera.presentation.theme.design_sytem

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle

data class AppTypography(
    val light40: TextStyle,
    val light96: TextStyle,
    val regular32: TextStyle
)

val LocalAppTypography = staticCompositionLocalOf {
    AppTypography(
        light40 = TextStyle.Default,
        light96 = TextStyle.Default,
        regular32 = TextStyle.Default
    )
}