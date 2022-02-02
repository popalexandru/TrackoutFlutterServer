package com.example.app.services.implementations

import com.example.app.data.dto.RuneDdragon
import com.example.app.data.dto.match.Perks
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RunesService(
    private val client: HttpClient
) {
    var runesList = emptyList<RuneDdragon>()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            runesList = getSpells()
        }
    }

    fun getPerks(perks: Perks): List<RuneDdragon>{
        val runes = mutableListOf<RuneDdragon>()
        runesList.forEach {
            if(it.id == perks.styles?.get(0)?.selections?.get(0)?.perk!! || it.id == perks.styles.get(1).selections?.get(1)?.perk!!){
                runes.add(it)
            }
        }
        return runes
    }

    suspend fun getSpells() : List<RuneDdragon>{
        return try {
            client.get {
                url("https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perks.json")
            }
        } catch (e: Exception) {
            print("Error: " + e.message)
            return emptyList()
        }
    }
}