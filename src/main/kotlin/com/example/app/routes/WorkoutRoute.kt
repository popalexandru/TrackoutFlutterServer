package com.example.app.routes

import com.example.app.data.models.database.Workout
import com.example.app.data.models.requests.AddWaterRequest
import com.example.app.data.models.requests.FinishWorkoutRequest
import com.example.app.data.models.requests.StartWorkoutRequest
import com.example.app.data.models.requests.WorkoutRequest
import com.example.app.data.models.response.ExerciceResponse
import com.example.app.data.models.response.WorkoutReply
import com.example.app.data.repository.ExerciceRepository
import com.example.app.data.repository.WaterRepository
import com.example.app.data.repository.WorkoutRepository
import com.example.app.data.responses.WorkoutResponse
import com.example.app.others.utils.dateParam
import com.example.app.others.utils.userIdToken
import com.example.app.others.utils.workoutId
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.workoutRoute(
    workoutRepository: WorkoutRepository
){
    get("api/workout/dummy"){
        workoutRepository.createDummyWorkout()
        call.respond(
            HttpStatusCode.OK
        )
    }

    post("api/workout/start"){
        val request = call.receiveOrNull<StartWorkoutRequest>() ?: kotlin.run {
            call.respond(
                HttpStatusCode.BadRequest
            )
            return@post
        }
        workoutRepository.startWorkout(request)
        call.respond(
            HttpStatusCode.OK
        )
    }

    post("api/workout/finish"){
        val request = call.receiveOrNull<FinishWorkoutRequest>() ?: kotlin.run {
            call.respond(
                HttpStatusCode.BadRequest
            )
            return@post
        }
        workoutRepository.finishWorkout(request)
        call.respond(
            HttpStatusCode.OK
        )
    }

    post("api/workout/cancel"){
        val request = call.receiveOrNull<WorkoutRequest>() ?: kotlin.run {
            call.respond(
                HttpStatusCode.BadRequest
            )
            return@post
        }
        workoutRepository.cancelWorkout(request)
        call.respond(
            HttpStatusCode.OK
        )
    }
}

fun Route.getWorkout(
    workoutRepository: WorkoutRepository,
    exerciceRepository: ExerciceRepository,
    waterRepository: WaterRepository
){
    get("api/workout/exercices"){
        call.respond(
            HttpStatusCode.OK,
            exerciceRepository.getExerciceResponseByWorkout(call.workoutId)
        )
    }
    //authenticate {
        get("api/workout/get"){
            val workout = workoutRepository.getWorkoutByDate(call.dateParam, "12323")

            workout?.let { workout ->
                val workoutResponse = workoutToResponse(workout).apply {
                    this.exerciceList = exerciceRepository.getExerciceResponseByWorkout(workout.id)
                    var waterQt: Int = 0
                    waterRepository.getWaterByWorkout(workout.id).forEach {
                        waterQt += it.quantity
                    }

                    this.waterQty = waterQt
                }

                call.respond(
                    HttpStatusCode.OK,
                    WorkoutResponse(
                        workoutResponse,
                        "Ok"
                    )
                )
            }

            call.respond(
                HttpStatusCode.OK,
                WorkoutResponse(message = "Workout doesn't exist")
            )
        }
    //}
}

fun workoutToResponse(workout: Workout): WorkoutReply {
    return WorkoutReply(
        workout.id,
        workout.userId,
        workout.isWorkoutDone,
        workout.date,
        workout.timestampDone,
        timestampStarted = workout.timestampStarted
    )
}