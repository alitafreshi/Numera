package com.tafreshiali.numera.domain.model

sealed interface ParenthesesType {
    data object Opening : ParenthesesType
    data object Closing : ParenthesesType
}
