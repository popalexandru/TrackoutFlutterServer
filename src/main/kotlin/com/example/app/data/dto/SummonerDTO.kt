package com.example.app.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class SummonerDTO(
    val accountId: String,
    val id: String,
    val name: String,
    val profileIconId: Int,
    val puuid: String,
    val revisionDate: Long,
    val summonerLevel: Int
) {
    constructor() : this("", "", "",0, "", 0L, 0)

    fun isEmpty(): Boolean{
        return accountId.isEmpty() && id.isEmpty() && name.isEmpty() && profileIconId == 0 && puuid.isEmpty() && revisionDate == 0L && summonerLevel == 0
    }
}
