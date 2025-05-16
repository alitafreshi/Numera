package com.tafreshiali.numera.presentation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
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
    isDarkTheme: Boolean,
    updateTheme: (Boolean) -> Unit,
    viewModel: CalculatorViewModel = viewModel(),
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        /*.background(NumeraAppTheme.colorSchema.colorSurface)*/
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        CalculatorDisplay(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            currentExpression = viewModel.expression,
            calculationResult = viewModel.result,
            isDarkTheme = isDarkTheme,
            updateTheme = updateTheme,
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
        CalculatorMainScreen(
            modifier = Modifier.fillMaxSize(),
            isDarkTheme = isSystemInDarkTheme(),
            updateTheme = {},
        )
    }
}
