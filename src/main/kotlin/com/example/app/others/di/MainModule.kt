package com.example.app.others.di

import com.example.app.data.repository.ExerciceRepository
import com.example.app.data.repository.TestRepository
import com.example.app.data.repository.WaterRepository
import com.example.app.data.repository.WorkoutRepository
import com.example.app.others.constants.Constants
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val mainModule = module {
    single {
        val client = KMongo.createClient("mongodb+srv://sutzu:alumil@trackoutcluster.dy8hb.mongodb.net/"+Constants.DB_NAME+"?retryWrites=true&w=majority").coroutine

        client.getDatabase(Constants.DB_NAME)
    }

    single {
        TestRepository(get())
    }

    single{
        WorkoutRepository(get())
    }

    single{
        ExerciceRepository(get())
    }

    single{
        WaterRepository(get())
    }
}