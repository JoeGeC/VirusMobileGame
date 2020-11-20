package com.example.virusgame.game.player

import com.example.virusgame.MainActivity
import com.example.virusgame.R
import com.example.virusgame.SaveManager
import com.example.virusgame.SoundManager
import com.example.virusgame.game.EntityHandler
import com.example.virusgame.game.abilities.Ability
import com.example.virusgame.game.abilities.AbilityFactory
import com.example.virusgame.game.zombie.ZombieDamageHandler
import kotlin.math.pow

class Player {
    @Transient private lateinit var playerHandler: PlayerHandler
    var gold = 0
    var zombieHearts = 0
    var maxHealth = 10
    var currentHealth = maxHealth
    var attack = 1
    var alive = true
    @Transient var ability: Ability? = null
    var attackBuyValue: Int
        get(){ return ((attack + 9f).pow(1.3f) - 16).toInt() }
        set(value) {}
    var maxHealthBuyValue: Int
        get(){ return (maxHealth.toFloat().pow(1.2f) - 14).toInt() }
        set(value) {}

    fun setup(entityHandler: EntityHandler){
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
        alive = false
        playerHandler.onPlayerDeath()
    }

    fun upgradeAttack(): Boolean {
        if(zombieHearts >= attackBuyValue){
            zombieHearts -= attackBuyValue
            attack++
            SoundManager.playSfx(MainActivity.applicationContext(), R.raw.attack_upgrade)
            return true
        }
        return false
    }

    fun upgradeHealth(): Boolean {
        if(zombieHearts >= maxHealthBuyValue){
            zombieHearts -= maxHealthBuyValue
            maxHealth++
            currentHealth++
            SoundManager.playSfx(MainActivity.applicationContext(), R.raw.potion)
            return true
        }
        return false
    }

    fun useAbility(){
        ability?.use()
    }

    fun restoreHealth(healthToRestore: Int) {
        currentHealth += healthToRestore
        if(currentHealth > maxHealth) currentHealth = maxHealth
        SoundManager.playSfx(MainActivity.applicationContext(), R.raw.potion)
    }

    fun equipAbility(abilityName: String, zombieDamageHandler: ZombieDamageHandler) {
        val lastAbilityUseTime = ability?.lastAbilityUseTime
        ability = AbilityFactory().createAbility(abilityName, zombieDamageHandler)
        if(lastAbilityUseTime != null) ability?.lastAbilityUseTime = lastAbilityUseTime
        ability?.playSoundEffect()
    }

    fun revive() {
        restoreHealthToMax()
        ability = null
        gold = 0
        alive = true
    }


    private fun restoreHealthToMax() {
        currentHealth = maxHealth
    }
}