package com.tafreshiali.numera.presentation.components

import android.R.attr.text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tafreshiali.numera.domain.model.CalculatorAction
import com.tafreshiali.numera.presentation.model.CalculatorUiAction
import com.tafreshiali.numera.presentation.model.HighlightLevel
import com.tafreshiali.numera.presentation.theme.design_sytem.NumeraAppTheme

@Composable
fun CalculatorActionButtonComponent(
    modifier: Modifier = Modifier,
    uiAction: CalculatorUiAction,
    onClick: (CalculatorAction) -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(30.dp))
            .background(
                color = when (uiAction.highlightLevel) {
                    HighlightLevel.HighEmphasis -> NumeraAppTheme.colorSchema.colorHighEmphasisSurface
                    HighlightLevel.MediumEmphasis -> NumeraAppTheme.colorSchema.colorMediumEmphasisSurface
                    HighlightLevel.LowEmphasis -> NumeraAppTheme.colorSchema.colorLowEmphasisSurface
                }
            )
            .clickable { onClick(uiAction.action) },
        contentAlignment = Alignment.Center
    ) {
        when {
            uiAction.text != null -> {
                Text(
                    text = uiAction.text,
                    style = NumeraAppTheme.typography.regular32,
                    color = when (uiAction.highlightLevel) {
                        HighlightLevel.HighEmphasis -> NumeraAppTheme.colorSchema.colorHighEmphasisOnSurface
                        HighlightLevel.MediumEmphasis -> NumeraAppTheme.colorSchema.colorMediumEmphasisOnSurface
                        HighlightLevel.LowEmphasis -> NumeraAppTheme.colorSchema.colorLowEmphasisOnSurface
                    },
                    textAlign = TextAlign.Center,
                    maxLines = 1
                )
            }
            else -> uiAction.content()
        }
    }
}

@Preview
@Composable
private fun CalculatorActionButtonComponentPreview() {
    NumeraAppTheme {
        CalculatorActionButtonComponent(
            modifier = Modifier
                .aspectRatio(1f),
            uiAction = CalculatorUiAction(
                text = null,
                highlightLevel = HighlightLevel.LowEmphasis,
                action = CalculatorAction.Delete,
                content = {
                   Text(text = "Delete")
                }
            ),
            onClick = {}
        )
    }

}