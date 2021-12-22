package com.example.app.services

import com.example.app.data.dto.SummonerDTO
import io.ktor.client.*

interface SummonerServiceInterface{
    suspend fun getSummonerByName(summonerName: String): SummonerDTO
}