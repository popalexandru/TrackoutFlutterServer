package com.example.app.data.models.trackout.response

import com.example.app.data.models.trackout.database.Repetition
import kotlinx.serialization.Serializable

@Serializable
data class ExerciceResponse(
    val exerciceId: String,
    val workoutId: String,
    val name: String,
    val description: String,
    val image: String,
    val reps: List<Repetition>
)
