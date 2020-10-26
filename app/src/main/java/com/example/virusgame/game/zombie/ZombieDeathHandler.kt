package com.example.virusgame.game.zombie

interface ZombieDeathHandler {
    fun takeGold(gold: Int)
    fun spawnNewZombie()
}
