package com.example.app.services.implementations

import com.example.app.data.dto.SummonerDTO
import com.example.app.others.constants.Constants.GET_SUMMONER_URL_BY_NAME
import com.example.app.others.utils.API_KEY
import com.example.app.services.SummonerServiceInterface
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.util.*

class SummonerService(
    private val client: HttpClient
) : SummonerServiceInterface {

    @OptIn(InternalAPI::class)
    override suspend fun getSummonerByName(summonerName: String): SummonerDTO {
        return try {
            client.get {
                url(GET_SUMMONER_URL_BY_NAME + summonerName + "?api_key=$API_KEY")
            }
        } catch (e: Exception) {
            print("Error: " + e.message)
            return SummonerDTO();
        }
    }
}