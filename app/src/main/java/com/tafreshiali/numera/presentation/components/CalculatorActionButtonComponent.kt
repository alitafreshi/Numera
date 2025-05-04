package com.tafreshiali.numera.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
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
    val defaultModifier = modifier
        .clip(RoundedCornerShape(30.dp))
        .background(
            color = when (uiAction.highlightLevel) {
                HighlightLevel.HighEmphasis -> NumeraAppTheme.colorSchema.colorHighEmphasisSurface
                HighlightLevel.MediumEmphasis -> NumeraAppTheme.colorSchema.colorMediumEmphasisSurface
                HighlightLevel.LowEmphasis -> NumeraAppTheme.colorSchema.colorLowEmphasisSurface
            }
        )
        .semantics {
            contentDescription = when (uiAction.action) {
                is CalculatorAction.Number -> uiAction.action.number.toString()
                is CalculatorAction.Op -> uiAction.action.operation.symbol.toString()
                CalculatorAction.Calculate -> uiAction.text.orEmpty()
                CalculatorAction.Clear -> uiAction.text.orEmpty()
                CalculatorAction.Decimal -> uiAction.text.orEmpty()
                CalculatorAction.Delete -> uiAction.text.orEmpty()
                CalculatorAction.Parentheses -> uiAction.text.orEmpty()
            }
        }

    var isScaled by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isScaled) 0.75f else 1f,
        animationSpec = tween(durationMillis = 100),
        finishedListener = {
            // Return to normal scale after animation
            if (isScaled) {
                isScaled = false
            }
        }
    )

    if (uiAction.action == CalculatorAction.Delete) {
        CalculatorDeleteActionButtonComponent(
            modifier = defaultModifier,
            onDeleteClick = { onClick(uiAction.action) },
            content = uiAction.content
        )

    } else {
        Box(
            modifier = defaultModifier
                .clickable {
                    isScaled = true
                    onClick(uiAction.action)
                },
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
                        maxLines = 1,
                        modifier = Modifier.graphicsLayer(scaleX = scale, scaleY = scale)
                    )
                }

                else -> uiAction.content()
            }
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