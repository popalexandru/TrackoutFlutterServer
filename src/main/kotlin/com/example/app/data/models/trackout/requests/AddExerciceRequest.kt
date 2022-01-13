package com.example.app.data.models.trackout.requests

import kotlinx.serialization.Serializable

@Serializable
data class AddExerciceRequest(
    val exampleId: String,
    val workoutId: String
)
