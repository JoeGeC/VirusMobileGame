package com.example.virusgame.game.zombie.states

import android.graphics.Bitmap
import android.graphics.Canvas
import com.example.virusgame.clock.Clock
import com.example.virusgame.game.events.ZombieAttackEvent
import com.example.virusgame.game.zombie.Zombie
import com.example.virusgame.game.zombie.ZombieDamageCalculator

abstract class ZombieState(private var zombie: Zombie) {
    protected var frameNum: Int = 0
    var lastFrameUpdateTime: Long = 0
    open val animationSpeed = 100
    abstract val animation: List<Bitmap>
    abstract fun onAnimationFinish()

    open fun onSuccessfulSwipe() {
        zombie.state = HurtZombie(zombie)
        zombie.takeDamage(ZombieDamageCalculator.calculateDamage())
    }

    open fun update() {
        if(zombie.canAttack && Clock.haveMillisecondsPassedSince(zombie.lastAttackTime, zombie.attackTime * 5)){
            zombie.lastAttackTime = System.nanoTime()
            zombie.state = PreAttackZombie(zombie)
            ZombieAttackEvent.trigger()
        }
    }

    private fun getAnimationFrame(): Bitmap {
        if(Clock.haveMillisecondsPassedSince(lastFrameUpdateTime, animationSpeed)){
            lastFrameUpdateTime = System.nanoTime()
            frameNum++
            if(frameNum >= animation.size - 1) onAnimationFinish()
        }
        return animation[frameNum]
    }

    open fun draw(canvas: Canvas, x: Float, y: Float) {
        canvas.drawBitmap(getAnimationFrame(), x, y, null)
    }
}
