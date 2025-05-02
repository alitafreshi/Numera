package com.tafreshiali.numera.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.tafreshiali.numera.domain.model.CalculatorAction
import com.tafreshiali.numera.domain.usecase.ExpressionWriter
import java.text.NumberFormat
import java.util.Locale

class CalculatorViewModel(private val writer: ExpressionWriter = ExpressionWriter()) : ViewModel() {

    var result by mutableStateOf("")
        private set

    var expression by mutableStateOf("")
        private set

    fun onAction(action: CalculatorAction) {
        writer.processAction(action)
        expression = writer.expression
        val calculationResult = writer.calculationResult
        result =
            calculationResult.ifEmpty { "" } ?: calculationResult.toDouble().formatWithCommas()
    }

    private fun Double.formatWithCommas(): String {
        return NumberFormat.getNumberInstance(Locale.US).format(this)
    }
}