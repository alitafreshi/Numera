package com.tafreshiali.numera.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tafreshiali.numera.presentation.components.CalculatorActionButtonsGridComponent
import com.tafreshiali.numera.presentation.components.CalculatorDisplay
import com.tafreshiali.numera.presentation.model.calculatorUiActions
import com.tafreshiali.numera.presentation.theme.design_sytem.NumeraAppTheme

@Composable
fun CalculatorMainScreen(
    modifier: Modifier = Modifier,
    viewModel: CalculatorViewModel = viewModel(),
) {
    var theme by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(NumeraAppTheme.colorSchema.colorSurface),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        CalculatorDisplay(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            currentExpression = viewModel.expression,
            calculationResult = viewModel.result,
            isDarkTheme = theme,
            updateTheme = {
                theme = !theme
            },
        )

        CalculatorActionButtonsGridComponent(
            modifier = Modifier.fillMaxWidth(),
            actions = calculatorUiActions,
            onActionClick = viewModel::onAction,
        )
    }
}

@PreviewLightDark
@Composable
private fun CalculatorMainScreenPreview() {
    NumeraAppTheme {
        CalculatorMainScreen()
    }
}
