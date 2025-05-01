package com.tafreshiali.numera.presentation.components

import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tafreshiali.numera.domain.model.CalculatorAction
import com.tafreshiali.numera.presentation.model.CalculatorUiAction

@Composable
fun CalculatorActionButtonsGridComponent(
    modifier: Modifier = Modifier,
    actions: List<CalculatorUiAction>,
    onActionClick: (CalculatorAction) -> Unit
) {

    FlowRow(
        modifier = modifier
            .fillMaxWidth()
            .align(Alignment.CenterVertically),
        maxItemsInEachRow = 4,
        horizontalArrangement = Arrangement.spacedBy(16.dp),

        itemVerticalAlignment = Alignment.CenterVertically,

        ) {

    }
}