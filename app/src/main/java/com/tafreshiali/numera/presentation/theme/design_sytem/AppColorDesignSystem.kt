package com.tafreshiali.numera.presentation.theme.design_sytem

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class AppColorSchema(
    val colorSurface: Color,
    val colorOnSurfacePrimary: Color,
    val colorOnSurfaceSecondary: Color,
    val colorHighEmphasisSurface: Color,
    val colorHighEmphasisOnSurface: Color,
    val colorMediumEmphasisSurface: Color,
    val colorMediumEmphasisOnSurface: Color,
    val colorLowEmphasisSurface: Color,
    val colorLowEmphasisOnSurface: Color
)

val LocalAppColorSchema = staticCompositionLocalOf {
    AppColorSchema(
        colorSurface = Color.Unspecified,
        colorOnSurfacePrimary = Color.Unspecified,
        colorOnSurfaceSecondary = Color.Unspecified,
        colorHighEmphasisSurface = Color.Unspecified,
        colorHighEmphasisOnSurface = Color.Unspecified,
        colorMediumEmphasisSurface = Color.Unspecified,
        colorMediumEmphasisOnSurface = Color.Unspecified,
        colorLowEmphasisSurface = Color.Unspecified,
        colorLowEmphasisOnSurface = Color.Unspecified
    )
}