package com.example.app.data.dto

import kotlinx.serialization.Serializable


/*class LeagueDTO : ArrayList<LeagueDTOItem>()*/
@Serializable
data class LeagueDTO(
    val leagues: ArrayList<LeagueDTOItem>
)

@Serializable
data class LeagueDTOItem(
    val freshBlood: Boolean,
    val hotStreak: Boolean,
    val inactive: Boolean,
    val leagueId: String,
    val leaguePoints: Int,
    val losses: Int,
    val queueType: String,
    val rank: String,
    val summonerId: String,
    val summonerName: String,
    val tier: String,
    val veteran: Boolean,
    val wins: Int
)