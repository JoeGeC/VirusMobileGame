package viruswiper.game.zombie

import android.content.Context
import viruswiper.game.EntityHandler
import viruswiper.game.zombie.bosses.HealthRecoveryZombieBoss
import viruswiper.game.zombie.bosses.InvulnerableZombieBoss
import viruswiper.game.zombie.bosses.NormalZombieBoss
import viruswiper.game.zombie.types.*
import kotlin.random.Random

class ZombieMaker {
    fun makeZombie(context: Context, entityHandler: EntityHandler, wave: Int, playerStrength: Int): Zombie? {
        val random = Random.nextInt(3)
        val zombie = when {
            wave == 1 -> NonAttackZombie(context, entityHandler)
            wave > 3 && random == 0 -> HealthRecoveryZombie(context, entityHandler)
            wave > 7 && random == 1 -> InvulnerableZombie(context, entityHandler)
            else -> NormalZombie(context, entityHandler)
        }
        zombie.afterInit()
        zombie.setStats(wave, playerStrength)
        return zombie
    }

    fun makeBossZombie(context: Context, entityHandler: EntityHandler, wave: Int, playerStrength: Int): Zombie?{
        val zombie = when {
            wave <= 3 || wave + 1 % 3 == 0 -> NormalZombieBoss(context, entityHandler)
            wave % 3 == 1 -> HealthRecoveryZombieBoss(context, entityHandler)
            wave > 7 && wave % 3 == 2 -> InvulnerableZombieBoss(context, entityHandler)
            else -> NormalZombieBoss(context, entityHandler)
        }
        zombie.afterInit()
        zombie.setStats(wave, playerStrength)
        return zombie
    }
}
