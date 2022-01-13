package com.example.app.data.models.trackout.requests

import kotlinx.serialization.Serializable

@Serializable
data class WorkoutRequest(
    val workoutId: String
)
