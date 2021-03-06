package com.example.app.data.models.trackout.database

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Workout(
    @BsonId
    val id: String = ObjectId().toString(),
    val userId: String,
    val isWorkoutDone: Boolean = false,
    val date: String,
    val timestampDone: Long = -1,
    val timestampStarted: Long = -1
)
