package com.tafreshiali.numera.domain.expression_writer

@JvmInline
value class CalculationResult(val value: Double) {
    fun convertIfWholeNumber(): String =
        (if (value % 1.0 == 0.0) value.toInt() else value).toString()
}
