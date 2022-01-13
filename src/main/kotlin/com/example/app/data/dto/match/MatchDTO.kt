package com.example.app.data.dto.match

import kotlinx.serialization.Serializable

@Serializable
data class MatchDTO(
    val info: Info? = Info(),
    val metadata: Metadata? = Metadata()
)