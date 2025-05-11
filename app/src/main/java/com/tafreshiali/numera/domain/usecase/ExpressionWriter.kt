package com.tafreshiali.numera.domain.usecase

import com.tafreshiali.numera.domain.model.CalculatorAction
import com.tafreshiali.numera.domain.model.Operation
import com.tafreshiali.numera.domain.model.operationSymbols

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
                return
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
                when {
                    shouldReplaceOperation() -> {
                        val lastExpressionChar = expression.lastOrNull()
                        if (lastExpressionChar != action.operation.symbol) {
                            expression = expression.dropLast(1) + action.operation.symbol
                        }
                    }

                    canEnterOperation(action.operation) -> {
                        expression += action.operation.symbol
                    }
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

    private fun isCalculable(currentAction: CalculatorAction): Boolean = (
        currentAction !=
            CalculatorAction.Calculate ||
            currentAction != CalculatorAction.Clear
        )

    private fun isValidExpression(): Boolean {
        val nonNumeric = expression.replace(Regex("[0-9.]"), "")
        val isExpression = nonNumeric.isNotEmpty()
        val lastExpressionChar = expression.lastOrNull() ?: return false
        return isExpression && (lastExpressionChar !in "$operationSymbols(")
    }

    private fun calculationResult() {
        val parser = ExpressionParser(prepareForCalculation())
        val evaluator = ExpressionEvaluator(parser.parse())
        calculationResult = convertIfWholeNumber(evaluator.evaluate()).toString()
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
        if (expression.isEmpty()) return false
        val lastExpressionChar = expression.lastOrNull() ?: return false
        return when {
            operation in listOf(Operation.ADD, Operation.SUBTRACT) -> {
                lastExpressionChar in "$operationSymbols()0123456789"
            }

            else -> lastExpressionChar in "0123456789)"
        }
    }

    private fun shouldReplaceOperation(): Boolean {
        if (expression.isEmpty()) return false
        val lastExpressionChar = expression.lastOrNull() ?: return false
        return lastExpressionChar in operationSymbols
    }

    fun convertIfWholeNumber(value: Double): Any = if (value % 1.0 == 0.0) {
        value.toInt()
    } else {
        value
    }
}
