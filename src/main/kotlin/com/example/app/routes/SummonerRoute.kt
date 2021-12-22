package com.example.app.routes

import com.example.app.services.implementations.DBService
import com.example.app.services.implementations.SummonerService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Route.summonerRoutes(){

    val summonerService: SummonerService by inject()
    val dbService : DBService by inject()

    get("/api/summoner/get/byname") {
        val summoner = summonerService.getSummonerByName("Esys")

        if(!summoner.isEmpty()){
            dbService.saveSummoner(summoner)
        }

        call.respond(
            HttpStatusCode.OK,
            summoner
        )
    }
}