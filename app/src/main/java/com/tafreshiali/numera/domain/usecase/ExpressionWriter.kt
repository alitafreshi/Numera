package com.tafreshiali.numera.domain.usecase

import androidx.core.text.isDigitsOnly
import com.tafreshiali.numera.domain.model.CalculatorAction
import com.tafreshiali.numera.domain.model.Operation
import com.tafreshiali.numera.domain.model.operationSymbols
import java.text.NumberFormat
import java.util.Locale

class ExpressionWriter {

    var expression = ""
    var calculationResult = ""

    fun processAction(action: CalculatorAction) {
        when (action) {
            CalculatorAction.Calculate -> {
                if (calculationResult.isNotEmpty()) {
                    expression = calculationResult
                    calculationResult = ""
                }
            }

            CalculatorAction.Clear -> {
                expression = ""
                calculationResult = ""
            }

            CalculatorAction.Decimal -> {
                if (canEnterDecimal()) {
                    expression += "."
                }
            }

            CalculatorAction.Delete -> {
                expression = expression.dropLast(1)
            }

            is CalculatorAction.Number -> {
                expression += action.number
            }

            is CalculatorAction.Op -> {
                if (canEnterOperation(action.operation)) {
                    expression += action.operation.symbol
                }
            }

            CalculatorAction.Parentheses -> {
                processParentheses()
            }
        }
        if (isCalculable(action)) {
            if (!isValidExpression()) {
                calculationResult = ""
                return
            }
            calculationResult()
        }
    }

    private fun isCalculable(currentAction: CalculatorAction): Boolean =
        (currentAction != CalculatorAction.Calculate || currentAction != CalculatorAction.Clear)

    private fun isValidExpression(): Boolean {
        val isExpression = !expression.isDigitsOnly()
        val lastExpressionChar = expression.lastOrNull()
        return isExpression && (lastExpressionChar != null && lastExpressionChar !in "$operationSymbols(")
    }

    private fun calculationResult() {
        val parser = ExpressionParser(prepareForCalculation())
        val evaluator = ExpressionEvaluator(parser.parse())
        calculationResult = evaluator.evaluate().formatWithCommas()
    }

    private fun Double.formatWithCommas(): String {
        return NumberFormat.getNumberInstance(Locale.US).format(this)
    }

    private fun prepareForCalculation(): String {
        val newExpression = expression.dropLastWhile {
            it in "$operationSymbols(."
        }
        if (newExpression.isEmpty()) {
            return "0"
        }
        return newExpression
    }

    private fun processParentheses() {
        val openingCount = expression.count { it == '(' }
        val closingCount = expression.count { it == ')' }
        expression += when {
            expression.isEmpty() ||
                    expression.last() in "$operationSymbols(" -> "("

            expression.last() in "0123456789)" &&
                    openingCount == closingCount -> return

            else -> ")"
        }
    }

    private fun canEnterDecimal(): Boolean {
        if (expression.isEmpty() || expression.last() in "$operationSymbols.()") {
            return false
        }
        return !expression.takeLastWhile {
            it in "0123456789."
        }.contains(".")
    }

    private fun canEnterOperation(operation: Operation): Boolean {
        if (operation in listOf(Operation.ADD, Operation.SUBTRACT)) {
            return expression.isEmpty() || expression.last() in "$operationSymbols()0123456789"
        }
        return expression.isNotEmpty() || expression.last() in "0123456789)"
    }
}