package com.tafreshiali.numera.domain.model

sealed interface CalculatorAction {
    data class Number(val number: Int) : CalculatorAction
    data class Op(val operation: Operation) : CalculatorAction
    data object Clear : CalculatorAction
    data object Delete : CalculatorAction
    data object Decimal : CalculatorAction
    data object Parentheses : CalculatorAction
    data object Calculate : CalculatorAction
}
