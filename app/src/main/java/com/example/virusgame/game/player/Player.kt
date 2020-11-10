package com.example.virusgame.game.player

import com.example.virusgame.SaveManager
import com.example.virusgame.game.abilities.Ability
import com.example.virusgame.game.EntityHandler

class Player(){
    @Transient private lateinit var playerHandler: PlayerHandler
    var gold = 0
    var maxHealth = 10
    var currentHealth = maxHealth
    var attack = 1
    @Transient var ability: Ability? = null
    var attackBuyValue: Int
        get(){ return ((attack + 9) * 2.5f).toInt() }
        set(value) {}
    var maxHealthBuyValue: Int
        get(){ return (maxHealth * 2.5f).toInt() }
        set(value) {}

    fun setupPlayer(entityHandler: EntityHandler){
        playerHandler = entityHandler
        ability = SaveManager.loadAbility(entityHandler)
        ability?.zombieDamageHandler = entityHandler
    }

    fun increaseGold(goldToAdd: Int){
        gold += goldToAdd
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
        if(gold >= attackBuyValue){
            gold -= attackBuyValue
            attack++
            return true
        }
        return false
    }

    fun upgradeHealth(): Boolean {
        if(gold >= maxHealthBuyValue){
            gold -= maxHealthBuyValue
            maxHealth++
            return true
        }
        return false
    }

    fun useAbility(){
        if(ability != null && ability!!.use())
            playerHandler.abilityUsed()
    }
}