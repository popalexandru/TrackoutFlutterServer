package com.example.app.routes

import com.example.app.data.dto.ChampionMasteryDto
import com.example.app.data.dto.LeagueDTOItem
import com.example.app.data.dto.match.MatchDTO
import com.example.app.data.models.SummonerResponse
import com.example.app.others.utils.summonerName
import com.example.app.services.implementations.*
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import org.koin.ktor.ext.inject

fun Route.summonerRoutes(){

    val summonerService: SummonerService by inject()
    val masteryService: ChampionMasteryService by inject()
    val matchService: MatchesService by inject()
    val leagueService: LeagueService by inject()
    val dbService : DBService by inject()

    get("/api/summoner/get/byname") {
        val summoner = summonerService.getSummonerByName(call.summonerName)
        print(call.summonerName)
        var masteries = emptyList<ChampionMasteryDto>()
        var leagues = emptyList<LeagueDTOItem>()
        val matchRefs = matchService.getMatchList(summoner.puuid)
        val matchList = mutableListOf<MatchDTO>()

        val tasks = listOf(
            async(Dispatchers.IO) { masteries = masteryService.getMasteriesBySummonerId(summoner.id) },
            async(Dispatchers.IO) { leagues = leagueService.getLeaguesBySummonerId(summoner.id)},
            async(Dispatchers.IO) { matchList.add(matchService.getMatchById(matchRefs[0])) },
            async(Dispatchers.IO) { matchList.add(matchService.getMatchById(matchRefs[1])) },
            async(Dispatchers.IO) { matchList.add(matchService.getMatchById(matchRefs[2])) },
            async(Dispatchers.IO) { matchList.add(matchService.getMatchById(matchRefs[3])) },
        )

        tasks.awaitAll()

        matchList.sortByDescending { it.info?.gameCreation }

        val summonerResponse = SummonerResponse(
            summonerDTO = summoner,
            masteries = masteries,
            matchList,
            leagues = leagues
        )

        if(!summoner.isEmpty()){
            dbService.saveSummoner(summoner)
        }

        call.respond(
            HttpStatusCode.OK,
            summonerResponse
        )
    }
}