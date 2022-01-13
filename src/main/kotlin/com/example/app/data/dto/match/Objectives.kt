package com.example.app.data.dto.match

import kotlinx.serialization.Serializable

@Serializable
data class Objectives(
    val baron: Baron? = Baron(),
    val champion: Champion? = Champion(),
    val dragon: Dragon? = Dragon(),
    val inhibitor: Inhibitor? = Inhibitor(),
    val riftHerald: RiftHerald? = RiftHerald(),
    val tower: Tower? = Tower()
)