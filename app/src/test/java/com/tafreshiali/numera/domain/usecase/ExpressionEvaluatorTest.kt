package com.tafreshiali.numera.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.tafreshiali.numera.domain.model.ExpressionPart
import com.tafreshiali.numera.domain.model.Operation
import org.junit.Test


class ExpressionEvaluatorTest {
    private lateinit var evaluator: ExpressionEvaluator

    @Test
    fun `simple expression is properly evaluated`() {
        //Given
        evaluator = ExpressionEvaluator(
            listOf(
                ExpressionPart.Number(3.0),
                ExpressionPart.Op(Operation.ADD),
                ExpressionPart.Number(5.0)
            )
        )
        //When
        val actual = evaluator.evaluate()
        //Then
        val expected = 8.0
        assertThat(expected).isEqualTo(actual)
    }
}