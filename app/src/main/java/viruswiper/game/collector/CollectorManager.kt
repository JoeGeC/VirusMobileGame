package viruswiper.game.collector

import android.content.Context
import viruswiper.game.player.Player

interface CollectorManager {
    var context: Context
    var player: Player
    fun destroyCollector(collector: Collector)
    fun addCollector(collector: Collector)
}
