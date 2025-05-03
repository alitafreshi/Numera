package com.tafreshiali.numera.presentation.theme.design_sytem

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle

data class AppTypography(
    val light25: TextStyle,
    val light40: TextStyle,
    val light36: TextStyle,
    val regular32: TextStyle
)

val LocalAppTypography = staticCompositionLocalOf {
    AppTypography(
        light25 = TextStyle.Default,
        light40 = TextStyle.Default,
        light36 = TextStyle.Default,
        regular32 = TextStyle.Default
    )
}