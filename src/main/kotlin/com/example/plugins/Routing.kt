package com.example.plugins

import com.example.app.data.repository.ExerciceRepository
import com.example.app.data.repository.TestRepository
import com.example.app.data.repository.WaterRepository
import com.example.app.data.repository.WorkoutRepository
import com.example.app.routes.exerciceRoute
import com.example.app.routes.workoutRoute
import com.example.app.routes.getWorkout
import com.example.app.routes.waterRoute
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.response.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {

    val testRepo: TestRepository by inject()
    val workoutRepository: WorkoutRepository by inject()
    val exerciceRepository: ExerciceRepository by inject()
    val waterRepository: WaterRepository by inject()

    routing {
        get("/") {
            call.respondText("Trackout Project!")

            testRepo.writeTest()
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
    }
}
