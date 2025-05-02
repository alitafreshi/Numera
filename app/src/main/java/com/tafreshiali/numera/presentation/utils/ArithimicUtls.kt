package com.tafreshiali.numera.presentation.utils

import java.text.NumberFormat
import java.util.Locale

fun Double.formatWithCommas(): String {
    return NumberFormat.getNumberInstance(Locale.US).format(this)
}