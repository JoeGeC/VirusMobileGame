package com.example.virusgame.game.zombie.states

import com.example.virusgame.game.vector2.FloatVector2
import com.example.virusgame.game.zombie.Zombie
import com.example.virusgame.game.zombie.ZombieAnimations

class DeadZombie(var zombie: Zombie) : ZombieState(zombie) {
    override val animation = ZombieAnimations(zombie.context).dieAnimation1()
    override val animationSpeed = 20

    override fun onAnimationFinish() {
        die()
    }

    private fun die() {
        val midTopZombiePosition = FloatVector2(zombie.position.x + animation[frameNum].width / 2, zombie.position.y)
        zombie.entityHandler.onZombieDeath(zombie.gold, zombie.hearts, midTopZombiePosition)
    }

    override fun onSuccessfulSwipe() {}

    override fun update() {}
}
