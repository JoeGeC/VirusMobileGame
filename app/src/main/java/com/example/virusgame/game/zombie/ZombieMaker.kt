package com.example.virusgame.game.zombie

import android.content.Context
import com.example.virusgame.game.EntityHandler
import com.example.virusgame.game.vector2.IntVector2
import com.example.virusgame.game.zombie.bosses.HealthRecoveryZombieBoss
import com.example.virusgame.game.zombie.bosses.InvulnerableZombieBoss
import com.example.virusgame.game.zombie.bosses.NormalZombieBoss
import com.example.virusgame.game.zombie.types.*
import kotlin.random.Random

class ZombieMaker {
    fun makeZombie(context: Context, entityHandler: EntityHandler, offset: IntVector2, wave: Int, playerStrength: Int): Zombie? {
        val random = Random.nextInt(3)
        val zombie = when {
            wave == 1 -> NonAttackZombie(context, entityHandler, offset)
            wave > 3 && random == 0 -> HealthRecoveryZombie(context, entityHandler, offset)
            wave > 7 && random == 1 -> InvulnerableZombie(context, entityHandler, offset)
            else -> NormalZombie(context, entityHandler, offset)
        }
        zombie.afterInit()
        zombie.setStats(wave, playerStrength)
        return zombie
    }

    fun makeBossZombie(context: Context, entityHandler: EntityHandler, offset: IntVector2, wave: Int, playerStrength: Int): Zombie?{
        val zombie = when {
            wave <= 3 || wave + 1 % 3 == 0 -> NormalZombieBoss(context, entityHandler, offset)
            wave % 3 == 1 -> HealthRecoveryZombieBoss(context, entityHandler, offset)
            wave > 7 && wave % 3 == 2 -> InvulnerableZombieBoss(context, entityHandler, offset)
            else -> NormalZombieBoss(context, entityHandler, offset)
        }
        zombie.afterInit()
        zombie.setStats(wave, playerStrength)
        return zombie
    }
}
