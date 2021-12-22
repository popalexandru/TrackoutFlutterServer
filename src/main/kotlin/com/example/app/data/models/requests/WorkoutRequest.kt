package com.example.app.data.models.requests

import kotlinx.serialization.Serializable

@Serializable
data class WorkoutRequest(
    val workoutId: String
)
