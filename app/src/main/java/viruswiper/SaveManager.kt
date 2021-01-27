package viruswiper

import android.content.Context
import viruswiper.game.GameStats
import viruswiper.game.player.Player
import viruswiper.game.zombie.ZombieDamageHandler
import viruswiper.game.abilities.Ability
import viruswiper.game.abilities.AbilityFactory
import viruswiper.game.events.EventManager
import viruswiper.shop.items.ShopItemSaveData
import viruswiper.vibrator.VibrateManager
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

    fun saveSettings() {
        sharedPref.edit().putFloat(
            context.getString(R.string.musicVolumePreferenceId),
            SoundManager.musicVolume
        ).apply()
        sharedPref.edit().putFloat(
            context.getString(R.string.sfxVolumePreferenceId),
            SoundManager.sfxVolume
        ).apply()
        sharedPref.edit().putBoolean(context.getString(R.string.vibratePreferenceId), VibrateManager.active).apply()
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
        if (eventJson!!.isEmpty()) eventManager.clearEvents()
        else eventManager.loadEvents(gson.fromJson(eventJson, object : TypeToken<Map<String, Any>>() {}.type))
    }

    fun loadShop(): List<ShopItemSaveData>?{
        val shopJson = sharedPref.getString(context.getString(R.string.shopPreferenceId), "")
        if (shopJson!!.isEmpty()) return null
        return gson.fromJson(shopJson, object : TypeToken<List<ShopItemSaveData>>() {}.type)
    }

    fun loadSettings(){
        SoundManager.musicVolume = sharedPref.getFloat(context.getString(R.string.musicVolumePreferenceId), 0.7f)
        SoundManager.sfxVolume = sharedPref.getFloat(context.getString(R.string.sfxVolumePreferenceId), 0.7f)
        VibrateManager.active = sharedPref.getBoolean(context.getString(R.string.vibratePreferenceId), true)
    }

    fun clearData() {
        with(sharedPref.edit()) {
            putString(context.getString(R.string.playerPreferenceId), "")
            putString(context.getString(R.string.abilityPreferenceId), "")
            putString(context.getString(R.string.statsPreferenceId), "")
            putString(context.getString(R.string.eventPreferenceId), "")
            putString(context.getString(R.string.shopPreferenceId), "").apply()
            apply()
        }
        sharedPref.edit().putString(context.getString(R.string.shopPreferenceId), "").apply()

    }
}