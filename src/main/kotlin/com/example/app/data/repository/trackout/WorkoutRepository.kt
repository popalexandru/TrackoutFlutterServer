package com.example.app.data.repository.trackout

import com.example.app.data.models.trackout.database.Repetition
import com.example.app.data.models.trackout.database.Workout
import com.example.app.data.models.trackout.requests.FinishWorkoutRequest
import com.example.app.data.models.trackout.requests.StartWorkoutRequest
import com.example.app.data.models.trackout.requests.WorkoutRequest
import com.mongodb.client.result.UpdateResult
import org.litote.kmongo.and
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.setValue

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