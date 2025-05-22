package com.tafreshiali.numera.domain.expression_writer

import com.tafreshiali.numera.domain.model.Operation
import com.tafreshiali.numera.domain.model.operationSymbols

@JvmInline
value class Expression(val value: String) {

    fun isValidExpression(): Boolean {
        val nonNumeric = value.replace(Regex("[0-9.]"), "")
        val isExpression = nonNumeric.isNotEmpty()
        val lastExpressionChar = value.lastOrNull() ?: return false
        return isExpression && (lastExpressionChar !in "$operationSymbols(")
    }

    fun canEnterDecimal(): Boolean {
        if (value.isEmpty() || value.last() in "$operationSymbols.()") return false
        return !value.takeLastWhile { it in "0123456789." }.contains(".")
    }

    fun shouldReplaceOperation(): Boolean {
        if (value.isEmpty()) return false
        val lastExpressionChar = value.lastOrNull() ?: return false
        return lastExpressionChar in operationSymbols
    }

    fun canEnterOperation(operation: Operation): Boolean {
        if (value.isEmpty()) return false
        val lastExpressionChar = value.lastOrNull() ?: return false
        return when {
            operation in listOf(Operation.ADD, Operation.SUBTRACT) -> {
                lastExpressionChar in "$operationSymbols()0123456789"
            }

            else -> lastExpressionChar in "0123456789)"
        }
    }

    fun prepareForCalculation(): String {
        val newExpression = value.dropLastWhile {
            it in "$operationSymbols(."
        }
        if (newExpression.isEmpty()) {
            return "0"
        }
        return newExpression
    }

    fun processParentheses(): String? {
        val openingCount = value.count { it == '(' }
        val closingCount = value.count { it == ')' }
        if (value.last() in "0123456789)" && openingCount == closingCount) return null
        return when {
            value.isEmpty() || value.last() in "$operationSymbols(" -> "("
            else -> ")"
        }
    }
}
