package com.example.app.services.implementations

import com.example.app.data.dto.Spell_Dragon
import com.example.app.others.utils.API_KEY
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.util.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class SpellsService(
    private val client: HttpClient
) {
    var spellsList = emptyList<Spell_Dragon>()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            spellsList = getSpells()
        }
    }

    fun getSpellsByIds(spell1: Int, spell2: Int): List<Spell_Dragon>{
        val spells = mutableListOf<Spell_Dragon>()
        spellsList.forEach {
            if(it.id == spell1 || it.id == spell2){
                spells.add(it)
            }
        }
        return spells
    }

    suspend fun getSpells() : List<Spell_Dragon>{
        return try {
            client.get {
                url("https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/summoner-spells.json")
            }
        } catch (e: Exception) {
            print("Error: " + e.message)
            return emptyList()
        }
    }
}