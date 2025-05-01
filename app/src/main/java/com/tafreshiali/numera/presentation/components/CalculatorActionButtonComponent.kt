package com.tafreshiali.numera.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.tafreshiali.numera.domain.model.CalculatorAction
import com.tafreshiali.numera.presentation.model.CalculatorUiAction
import com.tafreshiali.numera.presentation.model.HighlightLevel
import com.tafreshiali.numera.presentation.theme.design_sytem.NumeraAppTheme

@Composable
fun CalculatorActionButtonComponent(
    modifier: Modifier = Modifier,
    action: CalculatorUiAction,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(NumeraAppTheme.shape.medium)
            .background(
                color = when (action.highlightLevel) {
                    HighlightLevel.HighEmphasis -> NumeraAppTheme.colorSchema.colorHighEmphasisSurface
                    HighlightLevel.MediumEmphasis -> NumeraAppTheme.colorSchema.colorMediumEmphasisSurface
                    HighlightLevel.LowEmphasis -> NumeraAppTheme.colorSchema.colorLowEmphasisSurface
                }
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        when {
            action.text != null -> {
                Text(
                    text = action.text,
                    style = NumeraAppTheme.typography.regular32,
                    color = when (action.highlightLevel) {
                        HighlightLevel.HighEmphasis -> NumeraAppTheme.colorSchema.colorHighEmphasisOnSurface
                        HighlightLevel.MediumEmphasis -> NumeraAppTheme.colorSchema.colorMediumEmphasisOnSurface
                        HighlightLevel.LowEmphasis -> NumeraAppTheme.colorSchema.colorLowEmphasisOnSurface
                    },
                    textAlign = TextAlign.Center,
                    maxLines = 1
                )
            }

            else -> action.content
        }
    }
}

@Preview
@Composable
private fun CalculatorActionButtonComponentPreview() {
    NumeraAppTheme{
        CalculatorActionButtonComponent(
            action = CalculatorUiAction(
                text = "1",
                highlightLevel = HighlightLevel.LowEmphasis,
                action = CalculatorAction.Number(1)
            ),
            onClick = {}
        )
    }

}