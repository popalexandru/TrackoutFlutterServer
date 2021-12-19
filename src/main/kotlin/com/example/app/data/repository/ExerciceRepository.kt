package com.example.app.data.repository

import com.example.app.data.models.database.Example
import com.example.app.data.models.database.Exercice
import com.example.app.data.models.database.Repetition
import com.example.app.data.models.response.ExerciceResponse
import com.mongodb.client.result.InsertOneResult
import org.litote.kmongo.and
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class ExerciceRepository(
    db: CoroutineDatabase
) {
    val exercices = db.getCollection<Exercice>()
    val examples = db.getCollection<Example>()
    val repetitions = db.getCollection<Repetition>()

    suspend fun addExercice(
        exampleId: String,
        workoutId: String
    ): InsertOneResult{
        return exercices.insertOne(
            Exercice(
                exampleId,
                workoutId
            )
        )
    }

    suspend fun getExercicesByWorkout(
        workoutId: String
    ): List<Exercice>{
        return exercices.find(
            Exercice::workoutId eq workoutId
        ).toList()
    }

    suspend fun getExerciceResponseByWorkout(
        workoutId: String
    ): List<ExerciceResponse> {
        /* get all exercices added to workout */
        val exercices = exercices.find(Exercice::workoutId eq workoutId).toList()
        val exResponses = mutableListOf<ExerciceResponse>()

        /* for each ex get the example and repetitions */
        exercices.forEach {
            val example = examples.findOne(Example::id eq it.exampleId)
            example?.let {
                val reps = repetitions.find(
                    and(
                        Repetition::exerciceId eq it.id,
                        Repetition::workoutId eq workoutId
                    )
                ).toList()

                exResponses.add(
                    ExerciceResponse(
                        example.id,
                        workoutId,
                        example.name,
                        example.description,
                        example.image,
                        reps
                    )
                )
            }
        }

        return exResponses
    }

    suspend fun getExamples():List<Example>{
        return examples.find().toList()
    }

    suspend fun addDummyExample(){
        examples.insertOne(
            Example(
                name = "Name",
                description = "Description",
                image = ""
            )
        )
    }
}