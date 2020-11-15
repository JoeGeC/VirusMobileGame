package com.example.virusgame.game.collector

import com.example.virusgame.game.player.Player

interface CollectorManager {
    var player: Player
    fun destroyCollector(collector: Collector)
    fun addCollector(collector: Collector)
}
