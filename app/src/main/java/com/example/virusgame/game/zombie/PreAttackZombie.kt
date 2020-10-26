package com.example.virusgame.game.zombie

import android.graphics.Bitmap
import android.graphics.Canvas

class PreAttackZombie(var zombie: Zombie) : ZombieState {
    private var frameNum: Int = 0
    private var lastFrameUpdateTime: Long = 0
    override val animation: List<Bitmap> = ZombieAnimations(zombie.context).preAttackAnimation1()
    private val startTime: Long = System.nanoTime()

    override fun draw(canvas: Canvas, x: Float, y: Float) {
        canvas.drawBitmap(getAnimationFrame(), x, y, null)
        zombie.drawHealthBar(canvas)
    }

    override fun getAnimationFrame(): Bitmap {
        if(millisecondsHavePassed(100)){
            lastFrameUpdateTime = System.nanoTime()
            frameNum++
            if(frameNum > animation.size - 1) frameNum = animation.size - 1
        }
        return animation[frameNum]
    }

    override fun onSuccessfulSwipe() {

    }

    override fun update() {
        if(millisecondsHavePassed(3000)){
            zombie.state = AttackZombie(zombie)
        }
    }

    private fun millisecondsHavePassed(milliseconds: Int) = (System.nanoTime() - startTime) / 1000000 > milliseconds

}
