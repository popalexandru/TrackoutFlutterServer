package com.example.app.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class RuneDdragon(
    val endOfGameStatDescs: List<String>? = listOf(),
    val iconPath: String? = "",
    val id: Int? = 0,
    val longDesc: String? = "",
    val majorChangePatchVersion: String? = "",
    val name: String? = "",
    val shortDesc: String? = "",
    val tooltip: String? = ""
)