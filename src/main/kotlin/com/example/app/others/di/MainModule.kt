package com.example.app.others.di

import com.example.app.data.repository.ExerciceRepository
import com.example.app.data.repository.TestRepository
import com.example.app.data.repository.WaterRepository
import com.example.app.data.repository.WorkoutRepository
import com.example.app.others.constants.Constants
import com.example.app.services.implementations.DBService
import com.example.app.services.implementations.LeagueService
import com.example.app.services.implementations.SummonerService
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val mainModule = module {
    single {
        val client = KMongo.createClient("mongodb+srv://sutzu:alumil@trackoutcluster.dy8hb.mongodb.net/"+Constants.DB_NAME+"?retryWrites=true&w=majority").coroutine

        client.getDatabase(Constants.DB_NAME)
    }

    single {
        HttpClient(CIO) {
/*            defaultRequest {
                header("Content-Type", "application/json;charset=utf-8")
            }*/
            install(JsonFeature){
                serializer = KotlinxSerializer()
            }
        }
    }

    single {
        SummonerService(get())
    }

    single {
        LeagueService(get())
    }

    single {
        DBService(get())
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