package viruswiper.game.zombie.types

import android.content.Context
import viruswiper.game.EntityHandler
import viruswiper.game.zombie.animations.NormalZombieAnimations
import viruswiper.game.zombie.animations.ZombieAnimations

open class NormalZombie(context: Context, entityHandler: EntityHandler) : Zombie(context, entityHandler){
    override val animations: ZombieAnimations = NormalZombieAnimations(context)
}