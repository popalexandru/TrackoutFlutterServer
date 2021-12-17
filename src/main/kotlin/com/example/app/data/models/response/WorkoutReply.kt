package com.example.app.data.models.response

import com.example.app.data.models.database.Workout
import kotlinx.serialization.Serializable

@Serializable
data class WorkoutReply(
    var workoutId: String,
    var userId: String,
    var isWorkoutDone: Boolean = false,
    var date: String,
    var timestampDone: Long = -1,
    var exerciceList: List<ExerciceResponse> = emptyList(),
    var waterQty: Int = 0
)
