package com.example.app.data.dto

import kotlinx.serialization.Serializable

@Serializable
class LeagueDTO(
    val leagues: Set<LeagueDTOItem>
)

@Serializable
data class LeagueDTOItem(
    val freshBlood: Boolean = false,
    val hotStreak: Boolean = false,
    val inactive: Boolean = false,
    val leagueId: String = "",
    val leaguePoints: Int = 0,
    val losses: Int = 999,
    val queueType: String = "",
    val rank: String = "",
    val summonerId: String = "",
    val summonerName: String = "",
    val tier: String = "",
    val veteran: Boolean = false,
    val wins: Int = 0,
    val miniSeries: MiniSeriesDTO? = null
)

@Serializable
data class MiniSeriesDTO(
    val losses: Int,
    val progress: String,
    val target: Int,
    val wins: Int
)