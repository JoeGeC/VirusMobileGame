package com.example.virusgame.game

interface PlayerHandler {
    fun inflictPlayerDamage(damage: Int)
    fun onPlayerDeath()
}
