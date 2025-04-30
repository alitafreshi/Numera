package com.tafreshiali.numera.presentation.model

import androidx.compose.runtime.Composable
import com.tafreshiali.numera.domain.model.CalculatorAction

data class CalculatorUiAction(
    val text: String?,
    val highlightLevel: HighlightLevel,
    val action: CalculatorAction,
    val content: @Composable () -> Unit = {}
)

sealed interface HighlightLevel {
    data object HighEmphasis : HighlightLevel
    data object MediumEmphasis : HighlightLevel
    data object LowEmphasis : HighlightLevel
}
