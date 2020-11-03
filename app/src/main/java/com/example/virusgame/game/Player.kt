package com.example.virusgame.game

class Player(private var playerHandler: PlayerHandler){
    var gold = 0
    var maxHealth = 10
    var currentHealth = maxHealth
    var attack = 1

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
}