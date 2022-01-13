package com.example.app.data.models

import com.example.app.data.dto.ChampionMasteryDto
import com.example.app.data.dto.SummonerDTO
import com.example.app.data.dto.match.MatchDTO
import kotlinx.serialization.Serializable

@Serializable
data class SummonerResponse(
    val summonerDTO: SummonerDTO,
    val masteries: List<ChampionMasteryDto>,
    val matchReferences: List<MatchDTO>
)
