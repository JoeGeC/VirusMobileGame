package com.example.virusgame.game.collector

import android.content.Context
import com.example.virusgame.game.player.Player

interface CollectorManager {
    var context: Context
    var player: Player
    fun destroyCollector(collector: Collector)
    fun addCollector(collector: Collector)
}
