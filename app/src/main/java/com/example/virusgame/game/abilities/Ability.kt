package com.example.virusgame.game.abilities

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import com.example.virusgame.ScreenDimensions
import com.example.virusgame.clock.Clock

abstract class Ability(@Transient val context: Context) {
    abstract val name: String
    abstract val iconBitmap: Bitmap
    abstract val animationFrames: Array<Bitmap>
    abstract val coolDownTime: Int
    var lastAbilityUseTime: Long = 0
    private var lastFrameUpdateTime: Long = 0
    private var lastMoveTime: Long = 0
    private var frameNum = 0
    private var xPos = 0f
    private var active = false
    abstract fun effect()

    fun use(): Boolean {
        if(Clock.haveMillisecondsPassedSince(lastAbilityUseTime, coolDownTime)){
            lastAbilityUseTime = System.nanoTime()
            effect()
            active = true
            return true
        }
        return false
    }

    fun draw(canvas: Canvas){
        if(active){
            canvas.drawBitmap(getAnimationFrame(), getXPos(), getYPos(), null)
            if(xPos > ScreenDimensions.width) finishAnimation()
        }
    }

    private fun finishAnimation() {
        xPos = 0f - animationFrames[0].width
        active = false
    }

    private fun getYPos(): Float = ScreenDimensions.height / 2.2f

    private fun getXPos(): Float {
        if (Clock.haveMillisecondsPassedSince(lastMoveTime, 1)) {
            xPos += 50
            lastMoveTime = System.nanoTime()
        }
        return xPos
    }

    private fun getAnimationFrame() : Bitmap {
        if(Clock.haveMillisecondsPassedSince(lastFrameUpdateTime, 100)){
            lastFrameUpdateTime = System.nanoTime()
            frameNum++
            if(frameNum >= animationFrames.size) frameNum = 0
        }
        return animationFrames[frameNum]
    }
}
