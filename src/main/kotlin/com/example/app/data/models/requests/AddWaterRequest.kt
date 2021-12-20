package com.example.app.data.models.requests

import kotlinx.serialization.Serializable

@Serializable
data class AddWaterRequest(
    val userId: String = "12323",
    val workoutId: String,
    val waterQty: String,
    val date: String
)
