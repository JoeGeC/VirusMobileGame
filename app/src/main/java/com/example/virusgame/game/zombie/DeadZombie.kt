package com.example.virusgame.game.zombie

import android.graphics.Bitmap
import android.graphics.Canvas
import com.example.virusgame.game.Clock

class DeadZombie(var zombie: Zombie) : ZombieState {
    private var frameNum: Int = 0
    private var lastFrameUpdateTime: Long = 0
    override val animation = ZombieAnimations(zombie.context).dieAnimation1()

    override fun draw(canvas: Canvas, x: Float, y: Float){
        canvas.drawBitmap(getAnimationFrame(), x, y, null)
    }

    override fun getAnimationFrame(): Bitmap {
        if(Clock().millisecondsHavePassed(lastFrameUpdateTime, 20)){
            lastFrameUpdateTime = System.nanoTime()
            frameNum++
            if(frameNum > animation.size - 1) die()
        }
        return animation[frameNum]
    }

    private fun die() {
        zombie.entityHandler.takeGold(zombie.gold)
        zombie.entityHandler.takeExp(zombie.exp)
        zombie.entityHandler.spawnNewZombie()
        zombie.entityHandler.incrementZombieKillCount()
    }

    override fun onSuccessfulSwipe() {}

    override fun update() {}
}
