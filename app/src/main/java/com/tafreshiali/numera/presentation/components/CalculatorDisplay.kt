package com.tafreshiali.numera.presentation.components

import android.R.attr.text
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.tafreshiali.numera.presentation.theme.design_sytem.NumeraAppTheme

@Composable
fun CalculatorDisplay(
    modifier: Modifier = Modifier,
    currentExpression: String,
    calculationResult: String
) {
    ConstraintLayout(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .wrapContentHeight()
    ) {
        val currentExpressionFontSize = remember(currentExpression) {
            if (currentExpression.length <= 12) {
                40.sp
            } else {
                (40 - (currentExpression.length * 0.3f))
                    .coerceIn(25f, 40f)
                    .sp
            }
        }
        val (tvCalculationResult, tvCurrentExpression) = createRefs()
        Text(
            text = currentExpression,
            style = NumeraAppTheme.typography.light25.copy(
                fontSize = currentExpressionFontSize,
                color = NumeraAppTheme.colorSchema.colorOnSurfaceSecondary
            ),
            textAlign = TextAlign.End,
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(tvCurrentExpression) {
                    top.linkTo(parent.top)
                }
        )

        Text(
            text = calculationResult,
            style = NumeraAppTheme.typography.light96.copy(color = NumeraAppTheme.colorSchema.colorOnSurfacePrimary),
            textAlign = TextAlign.End,
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(tvCalculationResult) {
                    top.linkTo(tvCurrentExpression.bottom, margin = 16.dp)
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
            currentExpression = "1,2556546546564444444448.2",
            calculationResult = "6,291÷5"
        )
    }
}