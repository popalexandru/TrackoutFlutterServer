package com.example.app.routes

import com.example.app.data.models.requests.AddExerciceRequest
import com.example.app.data.repository.ExerciceRepository
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.addDummyExercice(
    exerciceRepository: ExerciceRepository
){
    get("api/example/dummy"){
        exerciceRepository.addDummyExample()
    }

    get("api/example/get/all"){
        val examples = exerciceRepository.getExamples()
        call.respond(
            HttpStatusCode.OK,
            examples
        )
        return@get
    }

    post("api/exercice/add"){
        val request = call.receiveOrNull<AddExerciceRequest>() ?: kotlin.run {
            call.respond(
                HttpStatusCode.BadRequest
            )
            return@post
        }

        exerciceRepository.addExercice(request.exampleId, request.workoutId)


        call.respond(
            HttpStatusCode.OK
        )
    }
}