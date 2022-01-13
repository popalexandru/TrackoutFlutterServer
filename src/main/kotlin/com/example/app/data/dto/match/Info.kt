package com.example.app.data.dto.match

import kotlinx.serialization.Serializable

@Serializable
data class Info(
    val gameCreation: Long? = 0,
    val gameDuration: Int? = 0,
    val gameEndTimestamp: Long? = 0,
    val gameId: Long? = 0,
    val gameMode: String? = "",
    val gameName: String? = "",
    val gameStartTimestamp: Long? = 0,
    val gameType: String? = "",
    val gameVersion: String? = "",
    val mapId: Int? = 0,
    val participants: List<Participant>? = listOf(),
    val platformId: String? = "",
    val queueId: Int? = 0,
    val teams: List<Team>? = listOf(),
    val tournamentCode: String? = ""
)