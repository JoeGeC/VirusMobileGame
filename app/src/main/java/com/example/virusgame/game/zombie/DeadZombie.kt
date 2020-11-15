package com.example.virusgame.game.zombie

import android.graphics.Bitmap
import android.graphics.Canvas
import com.example.virusgame.clock.Clock
import com.example.virusgame.game.events.IntroEvent
import com.example.virusgame.game.vector2.FloatVector2

class DeadZombie(var zombie: Zombie) : ZombieState {
    private var frameNum: Int = 0
    override var lastFrameUpdateTime: Long = 0
    override val animation = ZombieAnimations(zombie.context).dieAnimation1()

    override fun draw(canvas: Canvas, x: Float, y: Float){
        canvas.drawBitmap(getAnimationFrame(), x, y, null)
    }

    override fun getAnimationFrame(): Bitmap {
        if(Clock.haveMillisecondsPassedSince(lastFrameUpdateTime, 20)){
            lastFrameUpdateTime = System.nanoTime()
            frameNum++
            if(frameNum >= animation.size - 1) die()
        }
        return animation[frameNum]
    }

    private fun die() {
        val midTopZombiePosition = FloatVector2(zombie.position.x + animation[frameNum].width / 2, zombie.position.y)
        zombie.entityHandler.onZombieDeath(zombie.gold, zombie.hearts, midTopZombiePosition)
    }

    override fun onSuccessfulSwipe() {}

    override fun update() {}
}
