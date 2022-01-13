package com.example.app.data.dto.match

import kotlinx.serialization.Serializable

@Serializable
data class Selection(
    val perk: Int? = 0,
    val var1: Int? = 0,
    val var2: Int? = 0,
    val var3: Int? = 0
)