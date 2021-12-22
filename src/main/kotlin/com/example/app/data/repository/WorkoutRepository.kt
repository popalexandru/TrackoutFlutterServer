package com.example.app.data.repository

import com.example.app.data.models.database.Repetition
import com.example.app.data.models.database.Test
import com.example.app.data.models.database.Workout
import com.example.app.data.models.requests.FinishWorkoutRequest
import com.example.app.data.models.requests.StartWorkoutRequest
import com.example.app.data.models.requests.WorkoutRequest
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
    val repetitions = db.getCollection<Repetition>()

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

        workouts.insertOne(
            Workout(
                userId = user,
                date = date
            )
        )

        return workouts.findOne(
            and(
                Workout::userId eq user,
                Workout::date eq date
            )
        )
    }

    suspend fun finishWorkout(
        finishWorkoutRequest: FinishWorkoutRequest
    ): UpdateResult {

        workouts.updateOne(
            Workout::id eq finishWorkoutRequest.workoutId,
            setValue(Workout::timestampDone, finishWorkoutRequest.timestampDone)
        )
        return workouts.updateOne(
            Workout::id eq finishWorkoutRequest.workoutId,
            setValue(Workout::isWorkoutDone, true)
        )
    }

    suspend fun startWorkout(
        startWorkoutRequest: StartWorkoutRequest
    ): UpdateResult {
        return workouts.updateOne(
            Workout::id eq startWorkoutRequest.workoutId,
            setValue(Workout::timestampStarted, startWorkoutRequest.timestampStarted)
        )
    }

    suspend fun cancelWorkout(
        workoutRequest: WorkoutRequest
    ) {
        workouts.updateOne(
            Workout::id eq workoutRequest.workoutId,
            setValue(Workout::timestampStarted, -1)
        )
        repetitions.deleteMany(
            Repetition::workoutId eq workoutRequest.workoutId
        )
    }


    suspend fun createDummyWorkout() {
        workouts.insertOne(
            Workout(
                userId = "12323",
                date = "14/12/2021"
            )
        )
    }
}