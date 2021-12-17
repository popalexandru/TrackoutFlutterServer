package com.example.app.data.repository

import com.example.app.data.models.database.Water
import com.mongodb.client.result.InsertOneResult
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class WaterRepository(
    db: CoroutineDatabase
) {
    val water = db.getCollection<Water>()

    suspend fun addWater(
        workoutId: String,
        userId: String,
        waterQty: Int,
        date: String
    ): InsertOneResult {
        return water.insertOne(
            Water(
                workoutId,
                userId,
                date,
                waterQty
            )
        )
    }

    suspend fun getWaterByWorkout(
        workoutId: String
    ): List<Water>{
        return water.find(
            Water::workoutId eq workoutId
        ).toList()
    }

    suspend fun getWaterByDate(
        date: String
    ): List<Water>{
        return water.find(
            Water::date eq date
        ).toList()
    }
}