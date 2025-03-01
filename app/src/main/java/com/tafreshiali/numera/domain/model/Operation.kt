package com.tafreshiali.numera.domain.model

enum class Operation(val symbol: Char) {
    ADD('+'),
    SUBTRACT('-'),
    MULTIPLY('x'),
    DIVIDE('/'),
    PERCENT('%')
}

val operationsSymbols = Operation.entries.map { it.symbol }.joinToString("")

fun Char.operationFromSymbol(): Operation =
    Operation.entries.find { it.symbol == this } ?: throw IllegalArgumentException("Invalid symbol")