package com.example.app.data.dto.match

import kotlinx.serialization.Serializable

@Serializable
data class Ban(
    val championId: Int? = 0,
    val pickTurn: Int? = 0
)