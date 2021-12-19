package com.example.app.data.repository

import com.example.app.data.models.database.Test
import com.example.app.data.models.database.Workout
import com.mongodb.client.result.UpdateResult
import org.litote.kmongo.and
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.setValue
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class WorkoutRepository(
    db: CoroutineDatabase
) {
    val workouts = db.getCollection<Workout>()

    suspend fun getWorkoutByDate(
        date: String,
        user: String
    ): Workout? {
        val workout = workouts.findOne(
            and(
                Workout::userId eq user,
                Workout::date eq date
            )
        )
        workout?.let {
            return workout
        }

        var formatter = SimpleDateFormat("dd-MM-yyyy")


        workouts.insertOne(
            Workout(
                userId = user,
                date = date
            )
        )

        return workouts.findOne(and(
            Workout::userId eq user,
            Workout::date eq date
        ))
    }

    suspend fun finishWorkout(
        workoutId: String
    ): UpdateResult {
        return workouts.updateOne(
            Workout::id eq workoutId,
            setValue(Workout::isWorkoutDone,true)
        )
    }

    suspend fun createDummyWorkout(){
        workouts.insertOne(
            Workout(
                userId = "12323",
                date = "14/12/2021"
            )
        )
    }
}