package com.tafreshiali.numera.presentation.components

import android.R.attr.maxWidth
import android.R.attr.thumbOffset
import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tafreshiali.numera.R
import com.tafreshiali.numera.presentation.theme.design_sytem.NumeraAppTheme

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun ThemeSwitcherComponent(
    isDarkTheme: Boolean,
    updateTheme: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth(0.2f)
            .wrapContentHeight()
            .clip(CircleShape)
            .background(color = NumeraAppTheme.colorSchema.colorLowEmphasisSurface)
            .clickable { updateTheme() }
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
