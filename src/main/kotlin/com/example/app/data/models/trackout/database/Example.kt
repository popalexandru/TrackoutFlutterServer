package com.example.app.data.models.trackout.database

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class Example(
    @BsonId
    val id: String = ObjectId().toString(),
    val name: String,
    val description: String,
    val image: String,
    val groupId: String
)
