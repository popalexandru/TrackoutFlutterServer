package com.example.app.data.models.database

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Example(
    @BsonId
    val id: String = ObjectId().toString(),
    val name: String,
    val description: String,
    val image: String
)
