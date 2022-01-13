package com.example.app.data.models.trackout.database

import kotlinx.serialization.Serializable

@Serializable
data class Repetition(
    val workoutId: String,
    val exerciceId: String,
    val reps: Int,
    val weight: Int
)
