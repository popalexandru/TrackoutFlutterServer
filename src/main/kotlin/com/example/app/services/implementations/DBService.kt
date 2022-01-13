package com.example.app.services.implementations

import com.example.app.data.dto.SummonerDTO
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