package com.example.plugins

import com.example.app.data.repository.ExerciceRepository
import com.example.app.data.repository.TestRepository
import com.example.app.data.repository.WaterRepository
import com.example.app.data.repository.WorkoutRepository
import com.example.app.routes.*
import com.example.app.services.implementations.DBService
import com.example.app.services.implementations.SummonerService
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {

    val testRepo: TestRepository by inject()
    val workoutRepository: WorkoutRepository by inject()
    val exerciceRepository: ExerciceRepository by inject()
    val waterRepository: WaterRepository by inject()



    routing {
        get("/") {
            call.respondText { "Test" }
        }

        /*workout*/
        workoutRoute(
            workoutRepository
        )

        getWorkout(
            workoutRepository,
            exerciceRepository,
            waterRepository
        )

        /* exercice */
        exerciceRoute(
            exerciceRepository
        )

        waterRoute(
            waterRepository
        )

        summonerRoutes()
        leagueRoutes()
    }
}
