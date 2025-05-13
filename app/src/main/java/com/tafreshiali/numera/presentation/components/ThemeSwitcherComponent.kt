package com.tafreshiali.numera.presentation.components

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tafreshiali.numera.R
import com.tafreshiali.numera.presentation.theme.design_sytem.NumeraAppTheme

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun ThemeSwitcherComponent(modifier: Modifier = Modifier) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth(0.2f)
            .wrapContentHeight()
            .background(color = NumeraAppTheme.colorSchema.colorLowEmphasisSurface, CircleShape),
        contentAlignment = Alignment.Center,
    ) {
        val maxWidthDp = constraints.maxWidth.dp
        val thumbAnim by animateDpAsState(
            targetValue = maxWidthDp,
            finishedListener = {},
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 3.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.SpaceAround,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_light_theme),
                tint = NumeraAppTheme.colorSchema.colorHighEmphasisSurface,
                contentDescription = null,
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_dark_theme),
                tint = NumeraAppTheme.colorSchema.colorHighEmphasisSurface,
                contentDescription = null,
            )
        }

        Box(
            modifier = Modifier
                .size(24.dp)
                .background(
                    color = NumeraAppTheme.colorSchema.colorMediumEmphasisSurface,
                    CircleShape,
                ),
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
        ThemeSwitcherComponent()
    }
}
