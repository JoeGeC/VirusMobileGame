package com.example.virusgame.game.zombie

interface ZombieDeathHandler {
    fun takeGold(gold: Int)
    fun takeExp(exp: Int)
    fun spawnNewZombie()
    fun incrementZombieKillCount()
    val zombieKillCount: Int
}
