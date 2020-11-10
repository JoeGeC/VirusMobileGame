package com.example.virusgame.game.ui

import android.content.Context
import android.graphics.*
import androidx.core.content.ContextCompat
import com.example.virusgame.R
import com.example.virusgame.clock.Clock
import com.example.virusgame.game.abilities.Ability
import com.example.virusgame.game.vector2.FloatVector2

class AbilityUi(context: Context, screenDimensions: FloatVector2, borderBottom: Float) {
    private val coolDownPaint: Paint = Paint()
    private val abilityIconPos: FloatVector2 = FloatVector2(50f, borderBottom + 150f)
    private var iconRect: Rect? = null

    init{
        coolDownPaint.color = ContextCompat.getColor(context, R.color.transparentBlue)
        coolDownPaint.textAlign = Paint.Align.CENTER
        coolDownPaint.textSize = screenDimensions.y / 30.0f
    }

    fun draw(canvas: Canvas, ability: Ability){
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
