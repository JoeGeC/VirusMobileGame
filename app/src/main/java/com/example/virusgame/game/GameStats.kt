package com.example.virusgame.game

class GameStats {
    var wave: Int = 1
    var zombieKillCount = 0
    var zombieWaveKillCount = 0

    fun incrementZombieKillCount() {
        zombieKillCount++
        zombieWaveKillCount++
        if(zombieWaveKillCount == 10){
            wave++
            zombieWaveKillCount = 0
        }
    }
}