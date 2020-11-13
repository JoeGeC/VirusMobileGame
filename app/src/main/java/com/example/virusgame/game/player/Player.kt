package com.example.virusgame.game.player

import com.example.virusgame.MainActivity
import com.example.virusgame.R
import com.example.virusgame.SaveManager
import com.example.virusgame.SoundManager
import com.example.virusgame.game.EntityHandler
import com.example.virusgame.game.abilities.Ability
import kotlin.math.pow

class Player(){
    @Transient private lateinit var playerHandler: PlayerHandler
    var gold = 10000
    var bossHearts = 10000
    var maxHealth = 10
    var currentHealth = maxHealth
    var attack = 1
    @Transient var ability: Ability? = null
    var attackBuyValue: Int
        get(){ return ((attack + 9f).pow(1.3f) - 10).toInt() }
        set(value) {}
    var maxHealthBuyValue: Int
        get(){ return (maxHealth.toFloat().pow(1.2f) - 10).toInt() }
        set(value) {}

    fun setupPlayer(entityHandler: EntityHandler){
        playerHandler = entityHandler
        ability = SaveManager.loadAbility(entityHandler)
    }

    fun takeDamage(damage: Int){
        currentHealth -= damage
        if(currentHealth <= 0){
            currentHealth = 0
            die()
        }
    }

    private fun die(){
        playerHandler.onPlayerDeath()
    }

    fun restoreHealthToMax() {
        currentHealth = maxHealth
    }

    fun upgradeAttack(): Boolean {
        if(bossHearts >= attackBuyValue){
            bossHearts -= attackBuyValue
            attack++
            SoundManager.playSfx(MainActivity.applicationContext(), R.raw.attack_upgrade)
            return true
        }
        return false
    }

    fun upgradeHealth(): Boolean {
        if(bossHearts >= maxHealthBuyValue){
            bossHearts -= maxHealthBuyValue
            maxHealth++
            currentHealth++
            SoundManager.playSfx(MainActivity.applicationContext(), R.raw.potion)
            return true
        }
        return false
    }

    fun useAbility(){
        if(ability != null && ability!!.use())
            playerHandler.abilityUsed()
    }

    fun restoreHealth(healthToRestore: Int) {
        currentHealth += healthToRestore
        if(currentHealth > maxHealth) currentHealth = maxHealth
        SoundManager.playSfx(MainActivity.applicationContext(), R.raw.potion)
    }
}