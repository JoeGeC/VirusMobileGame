package com.example.virusgame.game.zombie.types

import android.content.Context
import com.example.virusgame.ScreenDimensions
import com.example.virusgame.clock.Clock
import com.example.virusgame.game.EntityHandler
import com.example.virusgame.game.vector2.FloatVector2
import com.example.virusgame.game.vector2.IntVector2
import com.example.virusgame.game.zombie.animations.HealthRecoveryZombieAnimations
import com.example.virusgame.game.zombie.animations.ZombieAnimations
import com.example.virusgame.game.zombie.states.AliveZombie
import com.example.virusgame.game.zombie.states.ZombieState

open class HealthRecoveryZombie(context: Context, entityHandler: EntityHandler, offset: IntVector2) :
    Zombie(context, entityHandler, offset) {
    private var lastHealthRecoverTime: Long = 0
    override val animations: ZombieAnimations = HealthRecoveryZombieAnimations(context)

    override fun update(azimuth: Double){
        super.update(azimuth)
        if(Clock.haveMillisecondsPassedSince(lastHealthRecoverTime, 500)){
            recoverHealth()
            lastHealthRecoverTime = System.nanoTime()
        }
    }

    private fun recoverHealth(){
        currentHealth += (maxHealth / 10).coerceAtLeast(1)
        if (currentHealth > maxHealth) currentHealth = maxHealth
    }
}
