package com.example.plugins

import com.example.app.data.repository.ExerciceRepository
import com.example.app.data.repository.TestRepository
import com.example.app.data.repository.WaterRepository
import com.example.app.data.repository.WorkoutRepository
import com.example.app.routes.addDummyExercice
import com.example.app.routes.createDummyWorkout
import com.example.app.routes.getWorkout
import com.example.app.routes.waterRoute
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import org.koin.ktor.ext.inject
import org.litote.kmongo.coroutine.CoroutineDatabase

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
        createDummyWorkout(
            workoutRepository
        )

        getWorkout(
            workoutRepository,
            exerciceRepository,
            waterRepository
        )

        /* exercice */
        addDummyExercice(
            exerciceRepository
        )

        waterRoute(
            waterRepository
        )
    }
}
