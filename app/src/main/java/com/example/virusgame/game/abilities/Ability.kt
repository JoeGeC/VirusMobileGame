package com.example.virusgame.game.abilities

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import com.example.virusgame.ScreenDimensions
import com.example.virusgame.SoundManager
import com.example.virusgame.clock.Clock
import com.example.virusgame.game.events.FirstAbilityEvent

abstract class Ability(@Transient val context: Context) {
    abstract val name: String
    abstract val iconBitmap: Bitmap
    abstract val animationFrames: Array<Bitmap>
    abstract val coolDownTime: Int
    abstract val soundEffectResource: Int
    var lastAbilityUseTime: Long = 0
    protected var lastFrameUpdateTime: Long = 0
    protected var frameNum = 0
    protected var active = false
    abstract fun effect()

    fun use(): Boolean {
        if(Clock.haveMillisecondsPassedSince(lastAbilityUseTime, coolDownTime)){
            lastAbilityUseTime = System.nanoTime()
            effect()
            active = true
            playSoundEffect()
            FirstAbilityEvent.onComplete()
            return true
        }
        return false
    }

    open fun draw(canvas: Canvas) {
        if (active) {
            canvas.drawBitmap(getAnimationFrame(), getXPos(), getYPos(), null)
        }
    }

    protected fun getYPos(): Float = ScreenDimensions.height / 1.8f - animationFrames[0].height / 2f

    protected fun getXPos(): Float = ScreenDimensions.width / 2f - animationFrames[0].width / 2f


    private fun getAnimationFrame() : Bitmap {
        if(Clock.haveMillisecondsPassedSince(lastFrameUpdateTime, 50)){
            lastFrameUpdateTime = System.nanoTime()
            frameNum++
            if(frameNum >= animationFrames.size) finishAnimation()
        }
        return animationFrames[frameNum]
    }

    protected fun finishAnimation() {
        frameNum = 0
        active = false
    }

    fun playSoundEffect(){
        SoundManager.playSfx(context, soundEffectResource)
    }
}
