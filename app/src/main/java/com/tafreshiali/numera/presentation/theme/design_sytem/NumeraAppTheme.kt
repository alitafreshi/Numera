package com.tafreshiali.numera.presentation.theme.design_sytem

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tafreshiali.numera.presentation.theme.colorDarkHighEmphasisSurface
import com.tafreshiali.numera.presentation.theme.colorDarkLowEmphasisSurface
import com.tafreshiali.numera.presentation.theme.colorDarkMediumEmphasisSurface
import com.tafreshiali.numera.presentation.theme.colorDarkOnSurfacePrimary
import com.tafreshiali.numera.presentation.theme.colorDarkOnSurfaceSecondary
import com.tafreshiali.numera.presentation.theme.colorDarkSurface
import com.tafreshiali.numera.presentation.theme.colorLightHighEmphasisOnSurface
import com.tafreshiali.numera.presentation.theme.colorLightHighEmphasisSurface
import com.tafreshiali.numera.presentation.theme.colorLightLowEmphasisSurface
import com.tafreshiali.numera.presentation.theme.colorLightMediumEmphasisSurface
import com.tafreshiali.numera.presentation.theme.colorLightOnSurfacePrimary
import com.tafreshiali.numera.presentation.theme.colorLightOnSurfaceSecondary
import com.tafreshiali.numera.presentation.theme.colorLightSurface

private val darkColorSchema = AppColorSchema(
    colorSurface = colorDarkSurface,
    colorOnSurfacePrimary = colorDarkOnSurfacePrimary,
    colorOnSurfaceSecondary = colorDarkOnSurfaceSecondary,
    colorHighEmphasisSurface = colorDarkHighEmphasisSurface,
    colorHighEmphasisOnSurface = colorDarkOnSurfacePrimary,
    colorMediumEmphasisSurface = colorDarkMediumEmphasisSurface,
    colorMediumEmphasisOnSurface = colorDarkOnSurfacePrimary,
    colorLowEmphasisSurface = colorDarkLowEmphasisSurface,
    colorLowEmphasisOnSurface = colorDarkOnSurfacePrimary
)

private val lightColorSchema = AppColorSchema(
    colorSurface = colorLightSurface,
    colorOnSurfacePrimary = colorLightOnSurfacePrimary,
    colorOnSurfaceSecondary = colorLightOnSurfaceSecondary,
    colorHighEmphasisSurface = colorLightHighEmphasisSurface,
    colorHighEmphasisOnSurface = colorLightHighEmphasisOnSurface,
    colorMediumEmphasisSurface = colorLightMediumEmphasisSurface,
    colorMediumEmphasisOnSurface = colorLightOnSurfacePrimary,
    colorLowEmphasisSurface = colorLightLowEmphasisSurface,
    colorLowEmphasisOnSurface = colorLightOnSurfacePrimary
)

private val typography = AppTypography(
    light40 = TextStyle(
        fontFamily = workSans,
        fontWeight = FontWeight.Light,
        fontSize = 40.sp
    ),
    light96 = TextStyle(
        fontFamily = workSans,
        fontWeight = FontWeight.Light,
        fontSize = 96.sp
    ),
    regular32 = TextStyle(
        fontFamily = workSans,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp
    )
)

private val shape = AppShape(
    medium = RoundedCornerShape(10.dp),
    large = RoundedCornerShape(24.dp)
)

data object NumeraAppTheme {
    val colorSchema: AppColorSchema
        @Composable get() = LocalAppColorSchema.current

    val typography: AppTypography
        @Composable get() = LocalAppTypography.current

    val shape: AppShape
        @Composable get() = LocalAppShape.current
}

@Composable
fun NumeraAppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = if (isDarkTheme) darkColorSchema else lightColorSchema
    CompositionLocalProvider(
        LocalAppColorSchema provides colorScheme,
        LocalAppTypography provides typography,
        LocalAppShape provides shape,
        content = content
    )
}