package com.example.virusgame.game.zombie

import android.graphics.Bitmap
import android.graphics.Canvas

class AliveZombie(private var zombie: Zombie) : ZombieState {
    private var frameNum : Int = 0
    private var lastFrameUpdateTime: Long = 0
    override val animation = ZombieAnimations(zombie.context).idleAnimation1()

    override fun getAnimationFrame() : Bitmap {
        if((System.nanoTime() - lastFrameUpdateTime) / 1000000 > 100){
            lastFrameUpdateTime = System.nanoTime()
            frameNum++
            if(frameNum > animation.size - 1) frameNum = 0
        }
        return animation[frameNum]
    }

    override fun draw(canvas: Canvas, x: Float, y: Float){
        canvas.drawBitmap(getAnimationFrame(), x, y, null)
        drawHealthBar(canvas)
    }

    private fun drawHealthBar(canvas: Canvas) {
        val healthBarStartPos = zombie.x + 50.0f
        val maxHealthBarStopPos = zombie.rect.right - 50.0f
        val healthBarStopPos: Float = try {
            (maxHealthBarStopPos - healthBarStartPos) / zombie.maxHealth * zombie.currentHealth + healthBarStartPos
        } catch (e: Exception) {
            healthBarStartPos
        }
        val healthBarHeight = zombie.y - 50.0f
        canvas.drawLine(healthBarStartPos, healthBarHeight, healthBarStopPos, healthBarHeight, zombie.zombieHealthPaint)
    }

    override fun onSuccessfulSwipe() {
        zombie.takeDamage(1)
    }
}

