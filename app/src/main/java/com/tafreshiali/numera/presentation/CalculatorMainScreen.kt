package com.tafreshiali.numera.presentation

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontVariation.weight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tafreshiali.numera.presentation.components.CalculatorActionButtonsGridComponent
import com.tafreshiali.numera.presentation.components.CalculatorDisplay
import com.tafreshiali.numera.presentation.model.calculatorUiActions
import com.tafreshiali.numera.presentation.theme.design_sytem.NumeraAppTheme

@Composable
fun CalculatorMainScreen(
    modifier: Modifier = Modifier,
    viewModel: CalculatorViewModel = viewModel()
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(NumeraAppTheme.colorSchema.colorSurface),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CalculatorDisplay(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            currentExpression = viewModel.expression,
            previousExpression = viewModel.previousExpression
        )

        CalculatorActionButtonsGridComponent(
            modifier = Modifier.fillMaxWidth(),
            actions = calculatorUiActions,
            onActionClick = viewModel::onAction
        )
    }
}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CalculatorMainScreenPreview() {
    NumeraAppTheme {
        CalculatorMainScreen()
    }
}