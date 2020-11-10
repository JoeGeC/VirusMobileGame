package com.example.virusgame.game.player

interface PlayerHandler {
    fun inflictPlayerDamage(damage: Int)
    fun onPlayerDeath()
    fun abilityUsed()
}
