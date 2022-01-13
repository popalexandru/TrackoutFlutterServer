package com.example.app.data.repository.trackout

import com.example.app.data.models.trackout.database.Test
import org.litote.kmongo.coroutine.CoroutineDatabase

class TestRepository(
    db: CoroutineDatabase
) {
    val test = db.getCollection<Test>()

    suspend fun writeTest(){
        test.insertOne(Test(test = "Test"))
    }
}