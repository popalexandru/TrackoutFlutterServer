package com.example.app.services.implementations

import com.example.app.data.dto.SummonerDTO
import com.example.app.data.models.database.Example
import com.example.app.data.models.database.Exercice
import com.example.app.data.models.database.Repetition
import com.example.app.data.models.response.ExerciceResponse
import com.mongodb.client.result.DeleteResult
import com.mongodb.client.result.InsertOneResult
import org.litote.kmongo.and
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class DBService(
    db: CoroutineDatabase
) {
    val summoners = db.getCollection<SummonerDTO>()

    suspend fun saveSummoner(summonerDTO: SummonerDTO){
        val summonerFound = summoners.findOne(SummonerDTO::id eq summonerDTO.id) != null

        if(!summonerFound){
            print("Inserting summoner in database")
            summoners.insertOne(summonerDTO)
        }else{
            print("Summoner already there")
        }
    }
}