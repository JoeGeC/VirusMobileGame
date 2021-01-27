package viruswiper.game.zombie

import viruswiper.game.player.Player
import kotlin.random.Random

object ZombieDamageCalculator{
    var player: Player? = null

    fun calculateDamage(): Int {
        return Random.nextInt((player!!.attack * 0.8).toInt(), (player!!.attack * 1.4).toInt()).coerceAtLeast(1)
    }
}
