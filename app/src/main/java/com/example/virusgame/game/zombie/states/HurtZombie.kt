package com.example.virusgame.game.zombie.states

import android.graphics.Bitmap
import android.graphics.Canvas
import com.example.virusgame.game.zombie.Zombie
import com.example.virusgame.game.zombie.ZombieAnimations

class HurtZombie(private var zombie: Zombie) : ZombieState(zombie) {
    override val animation: List<Bitmap> = ZombieAnimations(zombie.context).hurtAnimation1()
    override val animationSpeed = 20

    override fun draw(canvas: Canvas, x: Float, y: Float) {
        super.draw(canvas, x, y)
        zombie.drawHealth(canvas)
    }

    override fun onAnimationFinish() {
        zombie.state = AliveZombie(zombie)
    }
}