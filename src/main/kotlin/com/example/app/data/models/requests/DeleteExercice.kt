package com.example.app.data.models.requests

import kotlinx.serialization.Serializable

@Serializable
data class DeleteExercice(
    val exerciceId: String
)
