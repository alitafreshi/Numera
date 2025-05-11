package com.tafreshiali.numera.domain.model

enum class Operation(val symbol: Char) {
    ADD(symbol = '+'),
    SUBTRACT(symbol = '-'),
    MULTIPLY(symbol = 'x'),
    DIVIDE(symbol = '÷'),
    PERCENT(symbol = '%'),
}

val operationSymbols = Operation.entries.map { it.symbol }.joinToString("")

fun Char.operationFromSymbol(): Operation = Operation.entries.find { it.symbol == this }
    ?: throw IllegalArgumentException("Invalid symbol")
