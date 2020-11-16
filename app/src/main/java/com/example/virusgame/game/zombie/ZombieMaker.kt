package com.example.virusgame.game.zombie

import android.content.Context
import com.example.virusgame.game.EntityHandler
import com.example.virusgame.game.vector2.IntVector2
import com.example.virusgame.game.zombie.types.HealthRecoveryZombie
import com.example.virusgame.game.zombie.types.NonAttackZombie
import com.example.virusgame.game.zombie.types.NormalZombie
import com.example.virusgame.game.zombie.types.Zombie
import kotlin.random.Random

class ZombieMaker {
    fun makeZombie(context: Context, entityHandler: EntityHandler, offset: IntVector2, wave: Int, playerStrength: Int): Zombie? {
        val zombie = when {
            wave == 1 -> NonAttackZombie(context, entityHandler, offset)
            wave > 3 && Random.nextInt(2) == 1 -> HealthRecoveryZombie(context, entityHandler, offset)
            else -> NormalZombie(context, entityHandler, offset)
        }
        zombie.setStats(wave, playerStrength)
        return zombie
    }

    fun makeBossZombie(context: Context, entityHandler: EntityHandler, offset: IntVector2, wave: Int, playerStrength: Int): Zombie?{
        val zombie = ZombieBoss(context, entityHandler, offset)
        zombie.setStats(wave, playerStrength)
        return zombie
    }
}
