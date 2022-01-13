package com.example.app.data.models.trackout.database

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Test(
    @BsonId
    val id: String = ObjectId().toString(),
    val test: String
)
