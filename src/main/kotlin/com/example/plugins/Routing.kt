package com.example.plugins

import com.example.app.data.repository.trackout.ExerciceRepository
import com.example.app.data.repository.trackout.TestRepository
import com.example.app.data.repository.trackout.WaterRepository
import com.example.app.data.repository.trackout.WorkoutRepository
import com.example.app.routes.*
import com.example.app.services.implementations.SpellsService
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.response.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {

    val testRepo: TestRepository by inject()
    val workoutRepository: WorkoutRepository by inject()
    val exerciceRepository: ExerciceRepository by inject()
    val waterRepository: WaterRepository by inject()
    val spellsService: SpellsService by inject()


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

        summonerRoutes(spellsService)
    }
}
