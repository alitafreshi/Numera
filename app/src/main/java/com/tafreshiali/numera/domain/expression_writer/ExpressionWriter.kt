package com.tafreshiali.numera.domain.expression_writer

import com.tafreshiali.numera.domain.model.CalculatorAction
import com.tafreshiali.numera.domain.usecase.ExpressionEvaluator
import com.tafreshiali.numera.domain.usecase.ExpressionParser

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
                val expression = Expression(expression)
                if (expression.canEnterDecimal()) {
                    this.expression += "."
                }
            }

            CalculatorAction.Delete -> {
                expression = expression.dropLast(1)
            }

            is CalculatorAction.Number -> {
                expression += action.number
            }

            is CalculatorAction.Op -> {
                val expression = Expression(expression)
                when {
                    expression.shouldReplaceOperation() -> {
                        val lastExpressionChar = expression.value.lastOrNull()
                        if (lastExpressionChar != action.operation.symbol) {
                            this.expression = expression.value.dropLast(1) + action.operation.symbol
                        }
                    }

                    expression.canEnterOperation(action.operation) -> {
                        this.expression += action.operation.symbol
                    }
                }
            }

            CalculatorAction.Parentheses -> {
                val expression = Expression(expression)
                val processedParentheses = expression.processParentheses() ?: return
                this.expression += processedParentheses
            }
        }
        if (isCalculable(action)) {
            val expression = Expression(expression)
            if (!expression.isValidExpression()) {
                calculationResult = ""
                return
            }
            calculationResult()
        }
    }

    private fun isCalculable(currentAction: CalculatorAction): Boolean =
        (currentAction != CalculatorAction.Calculate || currentAction != CalculatorAction.Clear)

    private fun calculationResult() {
        val expression = Expression(expression)
        val parser = ExpressionParser(expression.prepareForCalculation())
        val evaluator = ExpressionEvaluator(parser.parse())
        calculationResult = CalculationResult(evaluator.evaluate()).convertIfWholeNumber()
    }
}
