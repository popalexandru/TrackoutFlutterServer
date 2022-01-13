package com.example.app.data.dto.match

import kotlinx.serialization.Serializable

@Serializable
data class Style(
    val description: String? = "",
    val selections: List<Selection>? = listOf(),
    val style: Int? = 0
)