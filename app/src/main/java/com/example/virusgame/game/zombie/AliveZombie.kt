package com.example.virusgame.game.zombie

import android.graphics.Bitmap
import android.graphics.Canvas
import com.example.virusgame.clock.Clock
import com.example.virusgame.game.events.ZombieAttackEvent

class AliveZombie(private var zombie: Zombie) : ZombieState {
    private var frameNum : Int = 0
    override var lastFrameUpdateTime: Long = 0
    override val animation = ZombieAnimations(zombie.context).idleAnimation1()

    override fun draw(canvas: Canvas, x: Float, y: Float){
        canvas.drawBitmap(getAnimationFrame(), x, y, null)
        zombie.drawHealth(canvas)
    }

    override fun getAnimationFrame() : Bitmap {
        if(Clock.haveMillisecondsPassedSince(lastFrameUpdateTime, 100)){
            lastFrameUpdateTime = System.nanoTime()
            frameNum++
            if(frameNum >= animation.size) frameNum = 0
        }
        return animation[frameNum]
    }

    override fun onSuccessfulSwipe() {
        zombie.takeDamage(ZombieDamageCalculator.calculateDamage())
    }

    override fun update() {
        if(zombie.canAttack && Clock.haveMillisecondsPassedSince(zombie.lastAttackTime, zombie.attackTime * 5)){
            zombie.lastAttackTime = System.nanoTime()
            zombie.state = PreAttackZombie(zombie)
            ZombieAttackEvent.trigger()
        }
    }
}

