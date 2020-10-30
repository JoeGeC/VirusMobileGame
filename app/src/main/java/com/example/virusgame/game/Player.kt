package com.example.virusgame.game

class Player(){
    var gold = 0
    var maxHealth = 10
    var currentHealth = maxHealth
    private var exp = 0
    var level = 1
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
        //TODO
    }

    fun earnExp(expEarned: Int) {
        exp += expEarned
        while(exp >= level * 50)
            levelUp()
    }

    private fun levelUp() {
        level++
        maxHealth = (10 + level * 1.5).toInt()
        currentHealth = maxHealth
        attack = (level * 1.5).toInt()
    }
}