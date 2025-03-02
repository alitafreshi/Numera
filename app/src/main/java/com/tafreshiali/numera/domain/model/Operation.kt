package com.tafreshiali.numera.domain.model

enum class Operation(val symbol: Char, val precedence: Int) {
    ADD(symbol = '+', precedence = 1),
    SUBTRACT(symbol = '-', precedence = 1),
    MULTIPLY(symbol = 'x', precedence = 2),
    DIVIDE(symbol = '/', precedence = 2),
    PERCENT(symbol = '%', precedence = 2)
}

val operationsSymbols = Operation.entries.map { it.symbol }.joinToString("")

fun Char.operationFromSymbol(): Operation =
    Operation.entries.find { it.symbol == this } ?: throw IllegalArgumentException("Invalid symbol")