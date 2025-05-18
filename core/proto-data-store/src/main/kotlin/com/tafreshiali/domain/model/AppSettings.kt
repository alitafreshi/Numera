package com.tafreshiali.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val selectedTheme: Boolean? = null
)