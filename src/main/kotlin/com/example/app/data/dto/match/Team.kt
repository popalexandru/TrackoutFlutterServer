package com.example.app.data.dto.match

import kotlinx.serialization.Serializable

@Serializable
data class Team(
    val bans: List<Ban>? = listOf(),
    val objectives: Objectives? = Objectives(),
    val teamId: Int? = 0,
    val win: Boolean? = false
)