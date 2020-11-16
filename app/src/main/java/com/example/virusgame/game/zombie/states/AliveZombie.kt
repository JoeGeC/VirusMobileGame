package com.example.virusgame.game.zombie.states

import android.graphics.Canvas
import com.example.virusgame.game.zombie.Zombie
import com.example.virusgame.game.zombie.ZombieAnimations

class AliveZombie(private var zombie: Zombie) : ZombieState(zombie) {
    override val animation = ZombieAnimations(zombie.context).idleAnimation1()

    override fun draw(canvas: Canvas, x: Float, y: Float){
        super.draw(canvas, x, y)
        zombie.drawHealth(canvas)
    }

    override fun onAnimationFinish() {
        frameNum = 0
    }
}

