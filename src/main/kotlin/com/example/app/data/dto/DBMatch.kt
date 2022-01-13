package com.example.app.data.dto

import com.example.app.data.dto.match.MatchDTO
import kotlinx.serialization.Serializable

@Serializable
data class DBMatch(
    val _id: Long,
    val match: MatchDTO
)
