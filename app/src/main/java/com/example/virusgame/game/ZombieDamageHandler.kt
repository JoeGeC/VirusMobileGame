package com.example.virusgame.game

import android.content.Context

interface ZombieDamageHandler {
    fun inflictZombieDamage(damage: Int)
    fun getPlayerAttack(): Int
    var context: Context
}
