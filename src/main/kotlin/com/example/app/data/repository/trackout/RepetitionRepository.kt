package com.example.app.data.repository.trackout

import com.example.app.data.models.trackout.database.Repetition
import com.mongodb.client.result.InsertOneResult
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class RepetitionRepository(
    db: CoroutineDatabase
) {
    val repetitions = db.getCollection<Repetition>()

    suspend fun addRepetition(
        exerciceId: String,
        workoutId: String,
        reps: Int,
        weight: Int
    ): InsertOneResult {
        return repetitions.insertOne(
            Repetition(
                workoutId, exerciceId, reps, weight
            )
        )
    }

    suspend fun getRepetitions(
        exerciceId: String
    ): List<Repetition>{
        return repetitions.find(Repetition::exerciceId eq exerciceId).toList()
    }
}