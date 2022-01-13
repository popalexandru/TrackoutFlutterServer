package com.example.app.data.dto.match

import kotlinx.serialization.Serializable

@Serializable
data class Perks(
    val statPerks: StatPerks? = StatPerks(),
    val styles: List<Style>? = listOf()
)