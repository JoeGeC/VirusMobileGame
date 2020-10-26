package com.example.virusgame.game

import android.content.Context

class Player(var context: Context){
    var gold = 0
    var maxHealth = 10
    var health = maxHealth
    private var exp = 0
    var level = 1
    var attack = 1

    fun increaseGold(goldToAdd: Int){
        gold += goldToAdd
    }

    fun takeDamage(damage: Int){
        health -= damage
        if(health <= 0){
            health = 0
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
        health = maxHealth
        attack = (level * 1.5).toInt()
    }
}