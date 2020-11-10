package com.example.virusgame.game.zombie

import android.content.Context

interface ZombieDamageHandler {
    fun inflictZombieDamage(damage: Int)
    fun getPlayerAttack(): Int
    var context: Context
}
