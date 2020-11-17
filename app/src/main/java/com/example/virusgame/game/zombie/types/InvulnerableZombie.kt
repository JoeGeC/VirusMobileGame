package com.example.virusgame.game.zombie.types

import android.content.Context
import com.example.virusgame.ScreenDimensions
import com.example.virusgame.game.EntityHandler
import com.example.virusgame.game.vector2.FloatVector2
import com.example.virusgame.game.vector2.IntVector2
import com.example.virusgame.game.zombie.animations.InvulnerableZombieAnimations
import com.example.virusgame.game.zombie.animations.ZombieAnimations
import com.example.virusgame.game.zombie.states.AliveZombie
import com.example.virusgame.game.zombie.states.ZombieState

open class InvulnerableZombie(context: Context, entityHandler: EntityHandler, offset: IntVector2) :
    Zombie(context, entityHandler, offset) {
    override val animations: ZombieAnimations = InvulnerableZombieAnimations(context)

    override fun onSuccessfulSwipe() {}

    override fun setNextAttackTime(): Int { return 0 }

    override fun setAttackSpeed(playerStrength: Int, wave: Int): Int {
        return (playerStrength * 1000) / wave
    }
}
