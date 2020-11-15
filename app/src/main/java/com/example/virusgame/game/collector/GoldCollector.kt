package com.example.virusgame.game.collector

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.virusgame.R
import com.example.virusgame.SoundManager
import com.example.virusgame.game.vector2.FloatVector2

class GoldCollector(position: FloatVector2, amount: Int, collectorManager: CollectorManager)
    : Collector(position, amount, collectorManager) {
    override val image: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.gold)

    override fun collect() {
        collectorManager.player.gold += amount
        SoundManager.playQueuedSfx(context, R.raw.gold)
    }
}