package com.example.app.data.models.requests

import kotlinx.serialization.Serializable

@Serializable
data class AddExerciceRequest(
    val exampleId: String,
    val workoutId: String
)
