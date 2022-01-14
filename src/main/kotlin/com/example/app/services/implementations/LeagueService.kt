package com.example.app.services.implementations

import com.example.app.data.dto.LeagueDTO
import com.example.app.data.dto.LeagueDTOItem
import com.example.app.data.dto.SummonerDTO
import com.example.app.others.constants.Constants.GET_SUMMONER_URL_BY_NAME
import com.example.app.others.utils.API_KEY
import com.example.app.services.SummonerServiceInterface
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.util.*

class LeagueService(
    private val client: HttpClient
){
    suspend fun getLeaguesBySummonerId(summonerId: String): List<LeagueDTOItem> {
        return try {
            client.get {
                url("https://eun1.api.riotgames.com/lol/league/v4/entries/by-summoner/$summonerId?api_key=$API_KEY")
            }
        } catch (e: Exception) {
            print("Error: " + e.message)
            return emptyList()
        }
    }
}