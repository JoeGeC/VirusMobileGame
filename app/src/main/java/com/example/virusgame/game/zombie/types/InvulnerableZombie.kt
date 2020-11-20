package com.example.virusgame.game.zombie.types

import android.content.Context
import com.example.virusgame.game.EntityHandler
import com.example.virusgame.game.events.FirstInvulnerableZombieEvent
import com.example.virusgame.game.vector2.IntVector2
import com.example.virusgame.game.zombie.animations.InvulnerableZombieAnimations
import com.example.virusgame.game.zombie.animations.ZombieAnimations

open class InvulnerableZombie(context: Context, entityHandler: EntityHandler, offset: IntVector2) :
    Zombie(context, entityHandler, offset) {
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
