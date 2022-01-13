package com.example.app.data.dto.match

import kotlinx.serialization.Serializable

@Serializable
data class Inhibitor(
    val first: Boolean? = false,
    val kills: Int? = 0
)