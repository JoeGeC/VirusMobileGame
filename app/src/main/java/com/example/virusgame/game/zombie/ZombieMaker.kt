package com.example.virusgame.game.zombie

import android.content.Context
import com.example.virusgame.game.EntityHandler
import com.example.virusgame.game.vector2.IntVector2

class ZombieMaker {
    fun makeZombie(context: Context, entityHandler: EntityHandler, offset: IntVector2, wave: Int, playerStrength: Int): Zombie? {
        val zombie = Zombie(context, entityHandler, offset)
        zombie.setStats(wave, playerStrength)
        return zombie
    }

    fun makeBossZombie(context: Context, entityHandler: EntityHandler, offset: IntVector2, wave: Int, playerStrength: Int): Zombie?{
        val zombie = ZombieBoss(context, entityHandler, offset)
        zombie.setStats(wave, playerStrength)
        return zombie
    }
}
