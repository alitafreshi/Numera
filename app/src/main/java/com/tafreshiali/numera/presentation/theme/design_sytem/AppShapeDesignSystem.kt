package com.tafreshiali.numera.presentation.theme.design_sytem

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape

data class AppShape(
    val medium: Shape,
    val large: Shape
)

val LocalAppShape = staticCompositionLocalOf {
    AppShape(
        medium = RectangleShape,
        large = RectangleShape
    )
}