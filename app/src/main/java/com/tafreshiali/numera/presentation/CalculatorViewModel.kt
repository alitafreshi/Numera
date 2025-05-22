package com.tafreshiali.numera.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tafreshiali.domain.AppProtoDataStore
import com.tafreshiali.domain.model.AppSettings
import com.tafreshiali.numera.domain.expression_writer.ExpressionWriter
import com.tafreshiali.numera.domain.model.CalculatorAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val dataStore: AppProtoDataStore<AppSettings>,
) : ViewModel() {

    private var writer: ExpressionWriter = ExpressionWriter()

    var result by mutableStateOf("")
        private set

    var expression by mutableStateOf("")
        private set

    fun onAction(action: CalculatorAction) {
        writer.processAction(action)
        expression = writer.expression
        result = writer.calculationResult
    }

    fun updateTheme(isDarkTheme: Boolean) {
        viewModelScope.launch {
            dataStore.setValue(AppSettings(selectedTheme = isDarkTheme))
        }
    }
}
