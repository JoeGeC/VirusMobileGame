package com.example.virusgame.game.zombie

import android.graphics.Bitmap
import android.graphics.Canvas
import com.example.virusgame.game.Clock
import kotlin.random.Random

class AliveZombie(private var zombie: Zombie) : ZombieState {
    private var frameNum : Int = 0
    private var lastFrameUpdateTime: Long = 0
    override val animation = ZombieAnimations(zombie.context).idleAnimation1()

    override fun draw(canvas: Canvas, x: Float, y: Float){
        canvas.drawBitmap(getAnimationFrame(), x, y, null)
        zombie.drawHealthBar(canvas)
    }

    override fun getAnimationFrame() : Bitmap {
        if(Clock().millisecondsHavePassed(lastFrameUpdateTime, 100)){
            lastFrameUpdateTime = System.nanoTime()
            frameNum++
            if(frameNum > animation.size - 1) frameNum = 0
        }
        return animation[frameNum]
    }

    override fun onSuccessfulSwipe() {
        zombie.takeDamage(1)
    }

    override fun update() {
        if(Random.nextInt(0, 350) == 1){
            zombie.state = PreAttackZombie(zombie)
        }
    }
}
