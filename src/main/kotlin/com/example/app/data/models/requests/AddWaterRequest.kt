package com.example.app.data.models.requests

import kotlinx.serialization.Serializable

@Serializable
data class AddWaterRequest(
    val userId: String,
    val workoutId: String,
    val waterQty: Int,
    val date: String
)
