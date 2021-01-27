package viruswiper.game.zombie.types

import android.content.Context
import viruswiper.clock.Clock
import viruswiper.game.EntityHandler
import viruswiper.game.events.FirstHealthRecoveryZombieEvent
import viruswiper.game.zombie.animations.HealthRecoveryZombieAnimations
import viruswiper.game.zombie.animations.ZombieAnimations

open class HealthRecoveryZombie(context: Context, entityHandler: EntityHandler) :
    Zombie(context, entityHandler) {
    private var lastHealthRecoverTime: Long = 0
    override val animations: ZombieAnimations = HealthRecoveryZombieAnimations(context)

    init {
        FirstHealthRecoveryZombieEvent.trigger()
    }

    override fun update(azimuth: Double){
        super.update(azimuth)
        if(Clock.haveMillisecondsPassedSince(lastHealthRecoverTime, 1000)){
            recoverHealth()
            lastHealthRecoverTime = System.nanoTime()
        }
    }

    private fun recoverHealth(){
        currentHealth += (maxHealth / 10).coerceAtLeast(1)
        if (currentHealth > maxHealth) currentHealth = maxHealth
    }

    override fun die(){
        FirstHealthRecoveryZombieEvent.completeEvent()
        super.die()
    }
}
