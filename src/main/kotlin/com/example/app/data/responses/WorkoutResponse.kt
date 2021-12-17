package com.example.app.data.responses

import com.example.app.data.models.response.WorkoutReply
import kotlinx.serialization.Serializable

@Serializable
data class WorkoutResponse(
    val workout: WorkoutReply? = null,
    val message: String = "Ok"
)
