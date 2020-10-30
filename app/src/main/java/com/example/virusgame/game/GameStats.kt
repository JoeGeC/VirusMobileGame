package com.example.virusgame.game

class GameStats {
    var wave: Int = 1
    var zombieKillCount = 0

    fun incrementZombieKillCount() {
        zombieKillCount++
        if(zombieKillCount % 10 == 0)
            wave++
    }
}