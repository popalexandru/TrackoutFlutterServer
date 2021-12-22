package com.example.app.routes

import com.example.app.services.implementations.LeagueService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Route.leagueRoutes(){
    val leagueService : LeagueService by inject()

    get("api/leagues/byid"){
        val leagues = leagueService.getLeaguesBySummonerId("VKRiPLEpHzKshIYf9NDiDAt2Z3YcE3PJcgpjMm-dsq37gDQ")

        call.respond(
            HttpStatusCode.OK,
            leagues
        )
    }
}