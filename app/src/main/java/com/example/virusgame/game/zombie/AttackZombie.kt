package com.example.virusgame.game.zombie

import android.graphics.Bitmap
import android.graphics.Canvas
import com.example.virusgame.R
import com.example.virusgame.SoundManager
import com.example.virusgame.clock.Clock
import com.example.virusgame.game.events.FirstDamageTaken

class AttackZombie(private val zombie: Zombie) : ZombieState {
    private var frameNum: Int = 0
    override var lastFrameUpdateTime: Long = 0
    override val animation: List<Bitmap> = ZombieAnimations(zombie.context).attackAnimation1()

    override fun draw(canvas: Canvas, x: Float, y: Float) {
        canvas.drawBitmap(getAnimationFrame(), x, y, null)
        zombie.drawHealth(canvas)
    }

    override fun getAnimationFrame(): Bitmap {
        if(Clock.haveMillisecondsPassedSince(lastFrameUpdateTime, 100)){
            lastFrameUpdateTime = System.nanoTime()
            frameNum++
            if(frameNum >= animation.size) attack()
        }
        return animation[frameNum]
    }

    private fun attack() {
        zombie.state = AliveZombie(zombie)
        zombie.entityHandler.inflictPlayerDamage(zombie.attack)
        FirstDamageTaken.trigger()
        SoundManager.playEffect(zombie.context, R.raw.damage)
    }

    override fun onSuccessfulSwipe() { }

    override fun update() { }
}
