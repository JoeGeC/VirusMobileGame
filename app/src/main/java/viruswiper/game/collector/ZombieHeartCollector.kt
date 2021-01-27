package viruswiper.game.collector

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import viruswiper.R
import viruswiper.SoundManager
import viruswiper.game.CollectorDoneListener
import viruswiper.game.vector2.FloatVector2

class ZombieHeartCollector(position: FloatVector2, amount: Int, collectorManager: CollectorManager, collectorDoneListener: CollectorDoneListener? = null) :
    Collector(position, amount, collectorManager, collectorDoneListener) {
    override val image: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.zombie_heart)

    override fun collect() {
        collectorManager.player.zombieHearts += amount
        SoundManager.playQueuedSfx(context, R.raw.boss_defeat)
    }
}
