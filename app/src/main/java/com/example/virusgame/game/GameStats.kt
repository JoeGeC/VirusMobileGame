package com.example.virusgame.game

import com.example.virusgame.WaveListener

class GameStats {
    private var wave: Int = 1
    fun getCurrentWave() = wave
    private var zombieKillCount = 0
    var zombieWaveKillCount = 0
    val waveAmount = 10
    @Transient lateinit var waveListener: WaveListener

    fun incrementZombieKillCount() {
        zombieKillCount++
        zombieWaveKillCount++
        if (zombieWaveKillCount == waveAmount) {
            increaseWave()
            zombieWaveKillCount = 0
        }
    }

    private fun increaseWave() {
        wave++
        waveListener.onWaveChange(wave)
    }

    fun resetWave() {
        wave = 1
        waveListener.onWaveChange(wave)
    }

    fun assignWaveListener(listener: WaveListener) {
        waveListener = listener
        waveListener.onWaveChange(wave)
    }
}