package com.example.virusgame.game

import com.example.virusgame.game.events.ShopOpensEvent

class GameStats {
    var wave: Int = 1
    var zombieKillCount = 0
    var zombieWaveKillCount = 0
    var waveAmount = 10

    fun incrementZombieKillCount() {
        zombieKillCount++
        zombieWaveKillCount++
        if (zombieWaveKillCount == waveAmount) {
            wave++
            zombieWaveKillCount = 0
        }
        if(wave >= 3) ShopOpensEvent.trigger()
    }
}