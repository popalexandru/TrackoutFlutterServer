package com.example.app.data.repository.trackout

import com.example.app.data.models.trackout.database.UserExample
import com.mongodb.client.result.InsertOneResult
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class UserExampleRepository(
    db: CoroutineDatabase
) {
    val usrEx = db.getCollection<UserExample>()

    suspend fun getUserExercices(
        userId: String
    ): List<UserExample>{
        return usrEx.find(
            UserExample::userId eq userId
        ).toList()
    }

    suspend fun addUserExercice(
        userId: String,
        name: String,
        image: String,
        desc: String,
        category: String
    ): InsertOneResult{
        return usrEx.insertOne(
            UserExample(
                userId, name, image, desc, category
            )
        )
    }
}