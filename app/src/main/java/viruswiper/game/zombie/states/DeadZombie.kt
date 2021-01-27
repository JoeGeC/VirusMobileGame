package viruswiper.game.zombie.states

import viruswiper.game.vector2.FloatVector2
import viruswiper.game.zombie.types.Zombie

class DeadZombie(var zombie: Zombie) : ZombieState(zombie) {
    override val animation = zombie.animations.dieAnimation()
    override val animationSpeed = 20

    override fun onAnimationFinish() {
        die()
    }

    private fun die() {
        frameNum = animation.size - 1
        val midTopZombiePosition = FloatVector2(zombie.position.x + animation[frameNum].width / 2, zombie.position.y)
        zombie.entityHandler.onZombieDeath(zombie.gold, zombie.hearts, midTopZombiePosition)
    }

    override fun onSuccessfulSwipe() {}

    override fun update() {}
}
