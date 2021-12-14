package com.example.app.data.repository

import org.litote.kmongo.coroutine.CoroutineDatabase

class TestRepository(
    db: CoroutineDatabase
) {
    val test = db.getCollection<String>()

    suspend fun writeTest(){
        test.insertOne("Test")
    }
}