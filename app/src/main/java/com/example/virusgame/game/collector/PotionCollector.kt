package com.example.virusgame.game.collector

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.virusgame.R
import com.example.virusgame.SoundManager
import com.example.virusgame.game.CollectorDoneListener
import com.example.virusgame.game.vector2.FloatVector2

class PotionCollector(position: FloatVector2, amount: Int, collectorManager: CollectorManager, collectorDoneListener: CollectorDoneListener? = null)
    : Collector(position, amount, collectorManager, collectorDoneListener) {
    override val image: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.red_potion_icon)

    override fun collect() {
        collectorManager.player.restoreHealth(amount)
        SoundManager.playQueuedSfx(context, R.raw.potion)
    }
}
