package com.tafreshiali.numera.domain.usecase

import com.tafreshiali.numera.domain.model.ExpressionPart
import com.tafreshiali.numera.domain.model.ParenthesesType
import com.tafreshiali.numera.domain.model.operationFromSymbol
import com.tafreshiali.numera.domain.model.operationsSymbols

class ExpressionParser(private val calculation: String) {

    fun parse(): List<ExpressionPart> {
        val result = mutableListOf<ExpressionPart>()
        var currentIndex = 0

        while (currentIndex < calculation.length) {
            val currentCharacter = calculation[currentIndex]
            when {
                currentCharacter in operationsSymbols ->
                    result.add(ExpressionPart.Op(currentCharacter.operationFromSymbol()))


                currentCharacter.isDigit() -> {
                    val (number, newIndex) = parseNumber(currentIndex)
                    result.add(number)
                    currentIndex = newIndex
                    continue
                }

                currentCharacter in "()" -> result.add(parseParentheses(currentCharacter))

            }
            currentIndex++
        }
        return result
    }

    private fun parseNumber(startingIndex: Int): Pair<ExpressionPart.Number, Int> {
        var currentIndex = startingIndex
        var dotCount = 0

        while (currentIndex < calculation.length) {
            val currentCharacter = calculation[currentIndex]
            when {
                currentCharacter.isDigit() -> currentIndex++
                currentCharacter == '.' -> {
                    if (dotCount == 1) throw IllegalArgumentException("Decimal number with Multiple decimal points is invalid")  // Stop if multiple decimal points appear
                    dotCount++
                    currentIndex++
                }
                else -> break
            }
        }

        val numberAsString = calculation.substring(startingIndex, currentIndex)
        return ExpressionPart.Number(numberAsString.toDouble()) to currentIndex
    }

    private fun parseParentheses(char: Char): ExpressionPart.Parentheses =
        ExpressionPart.Parentheses(
            when (char) {
                '(' -> ParenthesesType.Opening
                ')' -> ParenthesesType.Closing
                else -> throw IllegalArgumentException("Invalid parentheses type")
            }
        )
}
