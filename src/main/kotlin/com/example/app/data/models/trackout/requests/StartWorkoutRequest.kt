package com.example.app.data.models.trackout.requests

import kotlinx.serialization.Serializable

@Serializable
data class StartWorkoutRequest(
    val timestampStarted: Long,
    val workoutId: String
)
