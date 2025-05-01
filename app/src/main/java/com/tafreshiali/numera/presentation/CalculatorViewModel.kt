package com.tafreshiali.numera.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.tafreshiali.numera.domain.model.CalculatorAction
import com.tafreshiali.numera.domain.usecase.ExpressionWriter

class CalculatorViewModel(private val writer: ExpressionWriter = ExpressionWriter()) : ViewModel() {

    var result by mutableStateOf("")
        private set

    var expression by mutableStateOf("")
        private set

    fun onAction(action: CalculatorAction) {
        writer.processAction(action)
        expression = writer.expression
        result = writer.calculationResult
    }
}