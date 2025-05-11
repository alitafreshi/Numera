package com.tafreshiali.numera.domain.model

sealed interface ExpressionPart {
    data class Number(val number: Double) : ExpressionPart
    data class Op(val number: Operation) : ExpressionPart
    data class Parentheses(val type: ParenthesesType) : ExpressionPart
}
