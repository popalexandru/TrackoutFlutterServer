package com.example.app.data.models.requests

import kotlinx.serialization.Serializable

@Serializable
data class StartWorkoutRequest(
    val timestampStarted: Long,
    val workoutId: String
)
