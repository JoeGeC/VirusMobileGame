package com.example.virusgame.game.ui

import android.graphics.*
import com.example.virusgame.ScreenDimensions
import com.example.virusgame.clock.Clock
import com.example.virusgame.game.abilities.Ability
import com.example.virusgame.game.vector2.FloatVector2

class AbilityUi(borderBottom: Float) {
    private val coolDownPaint: Paint = Paint()
    private val abilityIconPos: FloatVector2 = FloatVector2(50f, borderBottom + 150f)
    private var iconRect: Rect? = null

    init{
        coolDownPaint.color = Color.argb(150, 0, 0, 255)
        coolDownPaint.textAlign = Paint.Align.CENTER
        coolDownPaint.textSize = ScreenDimensions.height / 30.0f
    }

    fun draw(canvas: Canvas, ability: Ability?){
        if(ability == null) return
        setIconRect(ability.iconBitmap)
        canvas.drawBitmap(ability.iconBitmap, abilityIconPos.x, abilityIconPos.y, null)
        canvas.drawRect(iconRect!!.left.toFloat(), coolDownBarPos(ability), iconRect!!.right.toFloat(), iconRect!!.bottom.toFloat(), coolDownPaint)
    }

    private fun setIconRect(iconBitmap: Bitmap) {
        if(iconRect == null){
            iconRect = Rect(
                (abilityIconPos.x).toInt(),
                (abilityIconPos.y).toInt(),
                (abilityIconPos.x + iconBitmap.width).toInt(),
                (abilityIconPos.y + iconBitmap.height).toInt()
            )
        }
    }

    private fun coolDownBarPos(ability: Ability): Float {
        val timeSinceAbilityUsed = Clock.millisecondsPassedSince(ability.lastAbilityUseTime)
        val result = (iconRect!!.bottom - iconRect!!.top).toFloat() / ability.coolDownTime * timeSinceAbilityUsed + iconRect!!.top
        if (result > iconRect!!.bottom) return iconRect!!.bottom.toFloat()
        return result
    }
}
