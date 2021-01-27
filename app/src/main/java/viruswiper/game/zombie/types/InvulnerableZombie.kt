package viruswiper.game.zombie.types

import android.content.Context
import viruswiper.game.EntityHandler
import viruswiper.game.events.FirstInvulnerableZombieEvent
import viruswiper.game.zombie.animations.InvulnerableZombieAnimations
import viruswiper.game.zombie.animations.ZombieAnimations

open class InvulnerableZombie(context: Context, entityHandler: EntityHandler) :
    Zombie(context, entityHandler) {
    override val animations: ZombieAnimations = InvulnerableZombieAnimations(context)

    init {
        FirstInvulnerableZombieEvent.trigger()
    }

    override fun onSuccessfulSwipe() {}

    override fun setNextAttackTime(): Int { return 0 }

    override fun setAttackSpeed(playerStrength: Int, wave: Int): Int {
        return (playerStrength * 1000) / wave
    }

    override fun die() {
        FirstInvulnerableZombieEvent.completeEvent()
        super.die()
    }
}
