package viruswiper.game.collector

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import viruswiper.R
import viruswiper.SoundManager
import viruswiper.game.CollectorDoneListener
import viruswiper.game.vector2.FloatVector2

class GoldCollector(position: FloatVector2, amount: Int, collectorManager: CollectorManager, collectorDoneListener: CollectorDoneListener? = null)
    : Collector(position, amount, collectorManager, collectorDoneListener) {
    override val image: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.gold)

    override fun collect() {
        collectorManager.player.gold += amount
        SoundManager.playQueuedSfx(context, R.raw.gold)
    }
}