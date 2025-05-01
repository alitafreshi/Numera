package com.tafreshiali.numera.presentation.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.tafreshiali.numera.presentation.theme.design_sytem.NumeraAppTheme

@Composable
fun CalculatorDisplay(
    modifier: Modifier = Modifier,
    currentExpression: String,
    previousExpression: String
) {
    ConstraintLayout(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .wrapContentHeight()
    ) {
        val (tvPreviousExpression, tvCurrentExpression) = createRefs()
        Text(
            text = previousExpression,
            style = NumeraAppTheme.typography.light40.copy(color = NumeraAppTheme.colorSchema.colorOnSurfaceSecondary),
            textAlign = TextAlign.End,
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(tvPreviousExpression) {
                    top.linkTo(parent.top)
                }
        )

        Text(
            text = currentExpression,
            style = NumeraAppTheme.typography.light96.copy(color = NumeraAppTheme.colorSchema.colorOnSurfacePrimary),
            textAlign = TextAlign.End,
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(tvCurrentExpression) {
                    top.linkTo(tvPreviousExpression.bottom, margin = 16.dp)
                }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun CalculatorDisplayPreview() {
    NumeraAppTheme {
        CalculatorDisplay(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f),
            currentExpression = "1,258.2",
            previousExpression = "6,291÷5"
        )
    }
}