package com.example.app.data.dto.match

import kotlinx.serialization.Serializable

@Serializable
data class Metadata(
    val dataVersion: String? = "",
    val matchId: String? = "",
    val participants: List<String>? = listOf()
)