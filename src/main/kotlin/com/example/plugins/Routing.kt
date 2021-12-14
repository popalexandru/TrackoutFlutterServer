package com.example.plugins

import com.example.app.data.repository.TestRepository
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import org.koin.ktor.ext.inject
import org.litote.kmongo.coroutine.CoroutineDatabase

fun Application.configureRouting() {

    val testRepo : TestRepository by inject()

    routing {
        get("/") {
                call.respondText("Trackout Project!")

            testRepo.writeTest()
            }
    }
}
