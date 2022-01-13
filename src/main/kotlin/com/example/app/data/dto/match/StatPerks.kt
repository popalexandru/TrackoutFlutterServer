package com.example.app.data.dto.match

import kotlinx.serialization.Serializable

@Serializable
data class StatPerks(
    val defense: Int? = 0,
    val flex: Int? = 0,
    val offense: Int? = 0
)