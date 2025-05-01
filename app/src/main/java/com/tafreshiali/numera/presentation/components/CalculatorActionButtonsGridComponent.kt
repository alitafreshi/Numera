package com.tafreshiali.numera.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tafreshiali.numera.domain.model.CalculatorAction
import com.tafreshiali.numera.presentation.model.CalculatorUiAction
import com.tafreshiali.numera.presentation.model.calculatorUiActions
import com.tafreshiali.numera.presentation.theme.design_sytem.NumeraAppTheme

@Composable
fun CalculatorActionButtonsGridComponent(
    modifier: Modifier = Modifier,
    actions: List<CalculatorUiAction>,
    onActionClick: (CalculatorAction) -> Unit
) {
    FlowRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .wrapContentHeight(Alignment.CenterVertically),
        maxItemsInEachRow = 4,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        val itemModifier = Modifier
            .aspectRatio(1f)
            .weight(1f)
            .align(Alignment.CenterVertically)
        repeat(actions.size) { index ->
            CalculatorActionButtonComponent(
                modifier = itemModifier,
                uiAction = actions[index],
                onClick = onActionClick
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun CalculatorActionButtonsGridComponentPreview() {
    NumeraAppTheme {
        CalculatorActionButtonsGridComponent(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f),
            actions = calculatorUiActions,
            onActionClick = {}
        )
    }
}