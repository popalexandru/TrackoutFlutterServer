package com.example.app.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class Spell_Dragon(
    val cooldown: Int? = 0,
    val description: String? = "",
    val gameModes: List<String>? = listOf(),
    val iconPath: String? = "",
    val id: Int? = 0,
    val name: String? = "",
    val summonerLevel: Int? = 0
)