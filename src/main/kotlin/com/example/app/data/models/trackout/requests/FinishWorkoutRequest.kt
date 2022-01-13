package com.example.app.data.models.trackout.requests

import kotlinx.serialization.Serializable

@Serializable
data class FinishWorkoutRequest(
    val workoutId: String,
    val timestampDone: Long
)
