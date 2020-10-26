package com.example.virusgame.game

import android.graphics.Bitmap
import android.graphics.Canvas

class DeadZombie(var zombie: Zombie) : ZombieState {
    private var frameNum: Int = 0
    private var lastFrameUpdateTime: Long = 0
    override val animation = ZombieAnimations(zombie.context).dieAnimation1()

    override fun getAnimationFrame(): Bitmap {
        if((System.nanoTime() - lastFrameUpdateTime) / 1000000 > 20){
            lastFrameUpdateTime = System.nanoTime()
            frameNum++
            if(frameNum > animation.size - 1) die()
        }
        return animation[frameNum]
    }

    override fun drawHealthBar(canvas: Canvas) {}

    private fun die() {
        zombie.deathHandler.takeGold(zombie.gold)
        zombie.deathHandler.spawnNewZombie()
    }
}
