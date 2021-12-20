package com.example.app.routes

import com.example.app.data.models.requests.AddExerciceRequest
import com.example.app.data.models.requests.AddWaterRequest
import com.example.app.data.repository.WaterRepository
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.waterRoute(
    waterRepository: WaterRepository
){

    post("api/water/add"){
        val request = call.receiveOrNull<AddWaterRequest>() ?: kotlin.run {
            call.respond(
                HttpStatusCode.BadRequest
            )
            return@post
        }

        waterRepository.addWater(
            request.workoutId,
            request.userId,
            Integer.parseInt(request.waterQty),
            request.date
        )
        call.respond(
            HttpStatusCode.OK
        )
    }
}