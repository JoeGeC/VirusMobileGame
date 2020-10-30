package com.example.virusgame

import android.content.Context
import com.example.virusgame.game.GameStats
import com.example.virusgame.game.Player
import com.example.virusgame.game.events.EventManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object SaveManager {
    private val context = MainActivity.applicationContext()
    private val gson = Gson()
    private val sharedPref = context.getSharedPreferences(context.getString(R.string.game_name_id), Context.MODE_PRIVATE)

    fun saveGame(player: Player, gameStats: GameStats, eventManager: EventManager){
        val playerJson = gson.toJson(player)
        val statsJson = gson.toJson(gameStats)
        val eventsJson = gson.toJson(eventManager.saveEvents())
        with (sharedPref.edit()) {
            putString(context.getString(R.string.playerPreferenceId), playerJson)
            putString(context.getString(R.string.statsPreferenceId), statsJson)
            putString(context.getString(R.string.eventPreferenceId), eventsJson)
            apply()
        }
    }

    fun loadPlayer(): Player{
        val playerJson = sharedPref.getString(context.getString(R.string.playerPreferenceId), "")
        if(playerJson!!.isEmpty()) return Player()
        return gson.fromJson(playerJson, Player::class.java)
    }

    fun loadGameStats(): GameStats{
        val statsJson = sharedPref.getString(context.getString(R.string.statsPreferenceId), "")
        if(statsJson!!.isEmpty()) return GameStats()
        return gson.fromJson(statsJson, GameStats::class.java)
    }

    fun loadEventManager(eventManager: EventManager) {
        val eventJson = sharedPref.getString(context.getString(R.string.eventPreferenceId), "")
        if(eventJson!!.isEmpty()) return
        eventManager.loadEvents(gson.fromJson(eventJson, object : TypeToken<Map<String, Any>>() {}.type))
    }
}