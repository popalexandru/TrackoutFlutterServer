package com.example.app.services.implementations

import com.example.app.data.dto.ChampionMasteryDto
import com.example.app.others.utils.API_KEY
import io.ktor.client.*
import io.ktor.client.request.*
import kotlin.text.get

class ChampionMasteryService (
    private val client: HttpClient
){
    suspend fun getMasteriesBySummonerId(encrtyptedSummonerId: String): List<ChampionMasteryDto> {
        return try {
            client.get {
                url("https://eun1.api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-summoner/$encrtyptedSummonerId?count=20&api_key=$API_KEY")
            }
        } catch (e: Exception) {
            print("Error: " + e.message)
            return emptyList()
        }
    }
}