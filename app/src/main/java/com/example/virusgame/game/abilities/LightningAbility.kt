package com.example.virusgame.game.abilities

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import com.example.virusgame.R
import com.example.virusgame.clock.Clock
import com.example.virusgame.game.zombie.ZombieDamageHandler

class LightningAbility(zombieDamageHandler: ZombieDamageHandler) : AttackAbility(zombieDamageHandler) {
    override val name: String = context.getString(R.string.lightningAbility)
    override val iconBitmap: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.lightning_ability_icon)
    override val coolDownTime: Int = 5000
    override val attackModifier: Float = 3f
    override val animationFrames = arrayOf<Bitmap>(
        BitmapFactory.decodeResource(context.resources, R.drawable.lightning_ability_frame1)
    )
    private var show = false

    override fun draw(canvas: Canvas) {
        if(active && showFrame())
            canvas.drawBitmap(animationFrames[0], getXPos(), getYPos(), null)
    }

    private fun showFrame(): Boolean {
        if(Clock.haveMillisecondsPassedSince(lastFrameUpdateTime, 200)){
            show = !show
            frameNum++
            if(frameNum >= 6) finishAnimation()
        }
        return show
    }
}
