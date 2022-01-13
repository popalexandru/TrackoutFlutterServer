package com.example.app.services.implementations

import com.example.app.data.dto.DBMatch
import com.example.app.data.dto.match.MatchDTO
import com.example.app.others.utils.API_KEY
import com.mongodb.client.model.InsertOneOptions
import io.ktor.client.*
import io.ktor.client.request.*
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.util.idValue

class MatchesService (
    private val client: HttpClient,
    private val db: CoroutineDatabase
){
    val matches = db.getCollection<DBMatch>()

    suspend fun getMatchList(puuid: String): List<String> {
        return try {
            client.get {
                url("https://europe.api.riotgames.com/lol/match/v5/matches/by-puuid/${puuid}/ids?start=0&count=20&api_key=$API_KEY")
            }
        } catch (e: Exception) {
            print("Error: " + e.message)
            return emptyList()
        }
    }

    suspend fun getMatchById(matchId: String): MatchDTO {
        getMatchFromDB(matchId)?.let {
            print("Match found in database :)")
            return it.match
        }

        return try {
            val match: MatchDTO = client.get {
                url("https://europe.api.riotgames.com/lol/match/v5/matches/${matchId}?api_key=$API_KEY")
            }
            saveMatchToDb(match)
            match
        } catch (e: Exception) {
            print("Error: " + e.message)
            return MatchDTO()
        }
    }

    private suspend fun getMatchFromDB(matchId: String): DBMatch? {
        val matchIdNumber = matchId.substring(matchId.indexOf('_') + 1).toLong()
        return matches.findOne(
            DBMatch::_id eq matchIdNumber
        )
    }

    private suspend fun saveMatchToDb(matchDTO: MatchDTO){
        matchDTO.info?.let {
            it.gameId?.let {
                matches.insertOne(
                    DBMatch(
                        it,
                        matchDTO
                    )
                )
            }
        }
    }
}