package com.example.virusgame.game.zombie

import android.graphics.Bitmap
import android.graphics.Canvas
import com.example.virusgame.clock.Clock
import com.example.virusgame.game.events.FirstKillEvent
import com.example.virusgame.game.events.FirstTimePlayingEvent

class DeadZombie(var zombie: Zombie) : ZombieState {
    private var frameNum: Int = 0
    private var lastFrameUpdateTime: Long = 0
    override val animation = ZombieAnimations(zombie.context).dieAnimation1()

    override fun draw(canvas: Canvas, x: Float, y: Float){
        canvas.drawBitmap(getAnimationFrame(), x, y, null)
    }

    override fun getAnimationFrame(): Bitmap {
        if(Clock.millisecondsHavePassedSince(lastFrameUpdateTime, 20)){
            lastFrameUpdateTime = System.nanoTime()
            frameNum++
            if(frameNum >= animation.size - 1) die()
        }
        return animation[frameNum]
    }

    private fun die() {
        FirstTimePlayingEvent.complete = true
        FirstKillEvent.trigger()
        zombie.entityHandler.takeGold(zombie.gold)
        zombie.entityHandler.incrementZombieKillCount()
        zombie.entityHandler.spawnNewZombie()
    }

    override fun onSuccessfulSwipe() {}

    override fun update() {}
}
