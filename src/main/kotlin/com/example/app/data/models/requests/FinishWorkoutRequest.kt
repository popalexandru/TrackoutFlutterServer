package com.example.app.data.models.requests

import kotlinx.serialization.Serializable

@Serializable
data class FinishWorkoutRequest(
    val workoutId: String,
    val timestampDone: Long
)
