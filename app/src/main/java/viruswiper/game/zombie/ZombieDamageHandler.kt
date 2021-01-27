package viruswiper.game.zombie

import android.content.Context

interface ZombieDamageHandler {
    fun inflictZombieDamage(damage: Int)
    fun getPlayerAttack(): Int
    fun shakeZombie()
    var context: Context
}
