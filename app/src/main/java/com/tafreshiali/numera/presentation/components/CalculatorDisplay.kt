package com.tafreshiali.numera.presentation.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
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
    val currentExpressionFontSize = remember(currentExpression) {
        if (currentExpression.length <= 12) {
            40.sp
        } else {
            (40 - (currentExpression.length * 0.3f))
                .coerceIn(25f, 40f)
                .sp
        }
    }

    ConstraintLayout(
        modifier = modifier
            .wrapContentHeight()
    ) {
        val (tvCalculationResult, tvCurrentExpression) = createRefs()
        TextField(
            value = currentExpression,
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(tvCurrentExpression) {
                    top.linkTo(parent.top)
                },
            readOnly = true,
            textStyle = NumeraAppTheme.typography.light25.copy(
                fontSize = currentExpressionFontSize,
                color = NumeraAppTheme.colorSchema.colorOnSurfaceSecondary,
                textAlign = TextAlign.End,
            ),
            maxLines = 1,
            singleLine = true,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                cursorColor = NumeraAppTheme.colorSchema.colorOnSurfacePrimary
            ),
            visualTransformation = ArithmeticVisualTransformation()
        )

        TextField(
            value = calculationResult,
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(tvCalculationResult) {
                    top.linkTo(tvCurrentExpression.bottom, margin = 16.dp)
                },
            readOnly = true,
            maxLines = 1,
            singleLine = true,
            textStyle = NumeraAppTheme.typography.light36.copy(
                color = NumeraAppTheme.colorSchema.colorOnSurfacePrimary,
                textAlign = TextAlign.End
            ),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                cursorColor = NumeraAppTheme.colorSchema.colorOnSurfacePrimary
            ),
            visualTransformation = ArithmeticVisualTransformation()
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
            calculationResult = "1255",
            currentExpression = "6291÷5"
        )
    }
}

class ArithmeticVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val originalText = text.text
        val tokens = tokenize(originalText)
        val builder = StringBuilder()
        val offsetPairs = mutableListOf<Pair<Int, Int>>()

        var originalIndex = 0
        var transformedIndex = 0

        for (token in tokens) {
            if (token.isNumber) {
//                val formatted = token.value.toDouble().formatWithCommas()
               val formatted = formatNumber(token.value)
                builder.append(formatted)
                offsetPairs.add(originalIndex to transformedIndex)
                originalIndex += token.value.length
                transformedIndex += formatted.length
            } else {
                builder.append(token.value)
                offsetPairs.add(originalIndex to transformedIndex)
                originalIndex += token.value.length
                transformedIndex += token.value.length
            }
        }
        offsetPairs.add(originalIndex to transformedIndex)

        val transformedText = builder.toString()

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                for ((orig, trans) in offsetPairs) {
                    if (offset <= orig) return trans
                }
                return transformedIndex
            }

            override fun transformedToOriginal(offset: Int): Int {
                for ((orig, trans) in offsetPairs) {
                    if (offset <= trans) return orig
                }
                return originalIndex
            }
        }

        return TransformedText(AnnotatedString(transformedText), offsetMapping)
    }


    private fun formatNumber(number: String): String {
        val parts = number.split(".", limit = 2)
        val integerPart = parts[0].let {
            if (it.isEmpty()) "0" else it
        }.reversed().chunked(3).joinToString(",").reversed()
        val decimalPart = if (parts.size > 1) "." + parts[1] else if (number.endsWith(".")) "." else ""
        return integerPart + decimalPart
    }

    private data class Token(val value: String, val isNumber: Boolean)

    private fun tokenize(text: String): List<Token> {
        val tokens = mutableListOf<Token>()
        var i = 0
        while (i < text.length) {
            if (text[i].isDigit() || text[i] == '.') {
                val start = i
                while (i < text.length && (text[i].isDigit() || text[i] == '.')) {
                    i++
                }
                val number = text.substring(start, i)
                tokens.add(Token(number, true))
            } else {
                tokens.add(Token(text[i].toString(), false))
                i++
            }
        }
        return tokens
    }
}