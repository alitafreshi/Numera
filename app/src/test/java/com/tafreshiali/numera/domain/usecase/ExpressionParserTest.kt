package com.tafreshiali.numera.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.tafreshiali.numera.domain.model.ExpressionPart
import com.tafreshiali.numera.domain.model.Operation
import com.tafreshiali.numera.domain.model.ParenthesesType
import org.junit.Test

class ExpressionParserTest {

    private lateinit var parser: ExpressionParser

    @Test
    fun `Plus expression is properly parsed`() {
        // Given
        parser = ExpressionParser("3 + 5")
        // When
        val actual = parser.parse()
        // Then
        val expected = listOf(
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.ADD),
            ExpressionPart.Number(5.0),
        )
        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `Minus expression is properly parsed`() {
        // Given
        parser = ExpressionParser("3 - 5")
        // When
        val actual = parser.parse()
        // Then
        val expected = listOf(
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Number(5.0),
        )
        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `Multiply expression is properly parsed`() {
        // Given
        parser = ExpressionParser("3 x 5")
        // When
        val actual = parser.parse()
        // Then
        val expected = listOf(
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Number(5.0),
        )
        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `Divide expression is properly parsed`() {
        // Given
        parser = ExpressionParser("3 / 5")
        // When
        val actual = parser.parse()
        // Then
        val expected = listOf(
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.DIVIDE),
            ExpressionPart.Number(5.0),
        )
        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `Parentheses expression is properly parsed`() {
        // Given
        parser = ExpressionParser("3 x (5 + 1)")
        // When
        val actual = parser.parse()
        // Then
        val expected = listOf(
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Parentheses(ParenthesesType.Opening),
            ExpressionPart.Number(5.0),
            ExpressionPart.Op(Operation.ADD),
            ExpressionPart.Number(1.0),
            ExpressionPart.Parentheses(ParenthesesType.Closing),
        )
        assertThat(expected).isEqualTo(actual)
    }
}
