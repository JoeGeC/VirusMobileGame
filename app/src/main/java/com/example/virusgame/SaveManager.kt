package com.example.virusgame

import android.content.Context
import com.example.virusgame.game.GameStats
import com.example.virusgame.game.player.Player
import com.example.virusgame.game.zombie.ZombieDamageHandler
import com.example.virusgame.game.abilities.Ability
import com.example.virusgame.game.abilities.AbilityFactory
import com.example.virusgame.game.events.EventManager
import com.example.virusgame.shop.items.ShopItemSaveData
import com.example.virusgame.shop.items.ShopItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object SaveManager {
    private val context = MainActivity.applicationContext()
    private val gson = Gson()
    private val sharedPref = context.getSharedPreferences(context.getString(R.string.game_name_id), Context.MODE_PRIVATE)

    fun saveGame(player: Player, gameStats: GameStats, eventManager: EventManager) {
        val playerJson = gson.toJson(player)
        val statsJson = gson.toJson(gameStats)
        val eventsJson = gson.toJson(eventManager.saveEvents())
        with(sharedPref.edit()) {
            putString(context.getString(R.string.playerPreferenceId), playerJson)
            putString(context.getString(R.string.abilityPreferenceId), player.ability?.name)
            putString(context.getString(R.string.statsPreferenceId), statsJson)
            putString(context.getString(R.string.eventPreferenceId), eventsJson)
            apply()
        }
    }

    fun saveShop(shopItems: List<ShopItemSaveData>){
        val shopJson = gson.toJson(shopItems)
        sharedPref.edit().putString(context.getString(R.string.shopPreferenceId), shopJson).apply()
    }

    fun loadPlayer(): Player {
        val playerJson = sharedPref.getString(context.getString(R.string.playerPreferenceId), "")
        if (playerJson!!.isEmpty()) return Player()
        return gson.fromJson(playerJson, Player::class.java)
    }

    fun loadAbility(zombieDamageHandler: ZombieDamageHandler): Ability? {
        val abilityName = sharedPref.getString(context.getString(R.string.abilityPreferenceId), "")
        return abilityName?.let { AbilityFactory().createAbility(it, zombieDamageHandler) }
    }

    fun loadGameStats(): GameStats {
        val statsJson = sharedPref.getString(context.getString(R.string.statsPreferenceId), "")
        if (statsJson!!.isEmpty()) return GameStats()
        return gson.fromJson(statsJson, GameStats::class.java)
    }

    fun loadEventManager(eventManager: EventManager) {
        val eventJson = sharedPref.getString(context.getString(R.string.eventPreferenceId), "")
        if (eventJson!!.isEmpty()) return
        eventManager.loadEvents(gson.fromJson(eventJson, object : TypeToken<Map<String, Any>>() {}.type))
    }

    fun loadShop(): List<ShopItemSaveData>?{
        val shopJson = sharedPref.getString(context.getString(R.string.shopPreferenceId), "")
        if (shopJson!!.isEmpty()) return null
        return gson.fromJson(shopJson, object : TypeToken<List<ShopItemSaveData>>() {}.type)
    }
}