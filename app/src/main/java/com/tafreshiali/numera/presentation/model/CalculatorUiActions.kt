package com.tafreshiali.numera.presentation.model

import androidx.compose.material3.Icon
import androidx.compose.ui.res.painterResource
import com.tafreshiali.numera.R
import com.tafreshiali.numera.domain.model.CalculatorAction
import com.tafreshiali.numera.domain.model.Operation
import com.tafreshiali.numera.presentation.theme.design_sytem.NumeraAppTheme

val calculatorUiActions = listOf(
    CalculatorUiAction(
        text = "C",
        highlightLevel = HighlightLevel.MediumEmphasis,
        action = CalculatorAction.Clear,
    ),
    CalculatorUiAction(
        text = "()",
        highlightLevel = HighlightLevel.MediumEmphasis,
        action = CalculatorAction.Parentheses,
    ),
    CalculatorUiAction(
        text = "%",
        highlightLevel = HighlightLevel.MediumEmphasis,
        action = CalculatorAction.Op(Operation.PERCENT),
    ),
    CalculatorUiAction(
        text = "÷",
        highlightLevel = HighlightLevel.HighEmphasis,
        action = CalculatorAction.Op(Operation.DIVIDE),
    ),
    CalculatorUiAction(
        text = "7",
        highlightLevel = HighlightLevel.LowEmphasis,
        action = CalculatorAction.Number(7),
    ),
    CalculatorUiAction(
        text = "8",
        highlightLevel = HighlightLevel.LowEmphasis,
        action = CalculatorAction.Number(8),
    ),
    CalculatorUiAction(
        text = "9",
        highlightLevel = HighlightLevel.LowEmphasis,
        action = CalculatorAction.Number(9),
    ),
    CalculatorUiAction(
        text = "×",
        highlightLevel = HighlightLevel.HighEmphasis,
        action = CalculatorAction.Op(Operation.MULTIPLY),
    ),
    CalculatorUiAction(
        text = "4",
        highlightLevel = HighlightLevel.LowEmphasis,
        action = CalculatorAction.Number(4),
    ),
    CalculatorUiAction(
        text = "5",
        highlightLevel = HighlightLevel.LowEmphasis,
        action = CalculatorAction.Number(5),
    ),
    CalculatorUiAction(
        text = "6",
        highlightLevel = HighlightLevel.LowEmphasis,
        action = CalculatorAction.Number(6),
    ),
    CalculatorUiAction(
        text = "-",
        highlightLevel = HighlightLevel.HighEmphasis,
        action = CalculatorAction.Op(Operation.SUBTRACT),
    ),
    CalculatorUiAction(
        text = "1",
        highlightLevel = HighlightLevel.LowEmphasis,
        action = CalculatorAction.Number(1),
    ),
    CalculatorUiAction(
        text = "2",
        highlightLevel = HighlightLevel.LowEmphasis,
        action = CalculatorAction.Number(2),
    ),
    CalculatorUiAction(
        text = "3",
        highlightLevel = HighlightLevel.LowEmphasis,
        action = CalculatorAction.Number(3),
    ),
    CalculatorUiAction(
        text = "+",
        highlightLevel = HighlightLevel.HighEmphasis,
        action = CalculatorAction.Op(Operation.ADD),
    ),
    CalculatorUiAction(
        text = ".",
        highlightLevel = HighlightLevel.LowEmphasis,
        action = CalculatorAction.Decimal,
    ),
    CalculatorUiAction(
        text = "0",
        highlightLevel = HighlightLevel.LowEmphasis,
        action = CalculatorAction.Number(0),
    ),
    CalculatorUiAction(
        text = null,
        content = {
            Icon(
                painter = painterResource(R.drawable.ic_remove_single_number_32),
                contentDescription = null,
                tint = NumeraAppTheme.colorSchema.colorOnSurfacePrimary,
            )
        },
        highlightLevel = HighlightLevel.LowEmphasis,
        action = CalculatorAction.Delete,
    ),
    CalculatorUiAction(
        text = "=",
        highlightLevel = HighlightLevel.HighEmphasis,
        action = CalculatorAction.Calculate,
    ),
)
