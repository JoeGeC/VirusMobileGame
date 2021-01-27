package viruswiper.game.zombie.types

import android.content.Context
import viruswiper.game.EntityHandler
import viruswiper.game.zombie.animations.NormalZombieAnimations

class NonAttackZombie(context: Context, entityHandler: EntityHandler) : Zombie(context, entityHandler){
    override val animations = NormalZombieAnimations(context)
    override var canAttack = false
}
