package com.example.app.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ChampionMasteryDto(
    val championId: Int,
    val championLevel: Int,
    val championPoints: Int,
    val championPointsSinceLastLevel: Int,
    val championPointsUntilNextLevel: Int,
    val chestGranted: Boolean,
    val lastPlayTime: Long,
    val summonerId: String,
    val tokensEarned: Int
)