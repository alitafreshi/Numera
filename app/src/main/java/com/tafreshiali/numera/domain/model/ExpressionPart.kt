package com.tafreshiali.numera.domain.model

sealed interface ExpressionPart {
    data class Number(val value: Double) : ExpressionPart
    data class Op(val value: Operation) : ExpressionPart
    data class Parentheses(val value: ParenthesesType) : ExpressionPart

}