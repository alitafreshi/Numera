package com.tafreshiali.numera.presentation.components

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tafreshiali.numera.R
import com.tafreshiali.numera.presentation.theme.design_sytem.NumeraAppTheme
import com.tafreshiali.numera.presentation.utils.clickableWithoutRipple
import kotlin.math.hypot

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun ThemeSwitcherComponent(
    isDarkTheme: Boolean,
    updateTheme: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    // Get screen dimensions
    val configuration = LocalWindowInfo.current
    val screenWidth = configuration.containerSize.width
    val screenHeight = configuration.containerSize.height
    // Calculate max radius to cover the entire screen (diagonal)
    val maxRadius = hypot(screenWidth.toDouble(), screenHeight.toDouble()).dp / 2

    val themeCircleRivalAnimation by animateDpAsState(
        targetValue = if (!isDarkTheme) maxRadius else 0.dp,
        animationSpec = tween(durationMillis = 200, easing = LinearEasing),
        label = "rippleRadius",
    )

    var themSwitcherCenter = remember { mutableStateOf(Offset.Unspecified) }

    val color by animateColorAsState(
        targetValue = NumeraAppTheme.colorSchema.colorSurface,
        animationSpec = tween(durationMillis = 200, easing = LinearEasing),
        label = "color",
    )

    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth(0.2f)
            .wrapContentHeight()
            .drawBehind {
                themSwitcherCenter.value = this.center
                drawRect(
                    color = color,
                    topLeft = Offset(
                        x = -screenWidth.toFloat(),
                        y = -screenHeight.toFloat(),
                    ),
                    size = Size(
                        width = screenWidth.toFloat() * 2,
                        height = screenHeight.toFloat() * 2,
                    ),
                )

                // Draw light circle on top
                drawCircle(
                    color = Color(0xFFF1F2F3),
                    radius = themeCircleRivalAnimation.toPx(),
                )
            }
            .background(color = NumeraAppTheme.colorSchema.colorLowEmphasisSurface, CircleShape)
            .clip(CircleShape)
            .clickableWithoutRipple(interactionSource = remember { MutableInteractionSource() }) {
                updateTheme(!isDarkTheme)
            }
            .padding(vertical = 4.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        val thumbOffset by animateDpAsState(
            targetValue = if (isDarkTheme) 0.dp + 4.dp else maxWidth - (24 + 4).dp,
            animationSpec = tween(durationMillis = 300),
            label = "thumbOffset",
        )

        val lightIconOffset by animateDpAsState(
            targetValue = if (!isDarkTheme) 8.dp else -maxWidth,
            animationSpec = tween(durationMillis = 300),
            label = "lightIconOffset",
        )

        val darkIconOffset by animateDpAsState(
            targetValue = if (isDarkTheme) (maxWidth - (24 + 8).dp) else maxWidth * 2,
            animationSpec = tween(durationMillis = 300),
            label = "darkIconOffset",
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_light_theme),
            contentDescription = "Light Mode",
            tint = NumeraAppTheme.colorSchema.colorHighEmphasisSurface,
            modifier = Modifier
                .graphicsLayer {
                    translationX = lightIconOffset.toPx()
                },
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_dark_theme),
            contentDescription = "Dark Mode",
            tint = NumeraAppTheme.colorSchema.colorHighEmphasisSurface,
            modifier = Modifier
                .graphicsLayer {
                    translationX = darkIconOffset.toPx()
                },
        )

        Box(
            modifier = Modifier
                .size(24.dp)
                .graphicsLayer {
                    translationX = thumbOffset.toPx()
                }
                .clip(CircleShape)
                .background(color = NumeraAppTheme.colorSchema.colorMediumEmphasisSurface),
        )
    }
}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
private fun ThemeSwitcherComponentPreview() {
    NumeraAppTheme {
        ThemeSwitcherComponent(isDarkTheme = false, updateTheme = {})
    }
}
