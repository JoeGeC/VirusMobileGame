package com.example.virusgame.game.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.content.ContextCompat
import com.example.virusgame.R
import com.example.virusgame.game.vector2.FloatVector2

class HealthUi (context: Context, private val screenDimensions: FloatVector2, private val borderBottom: Float){
    private val healthPaint: Paint = Paint()
    private val healthLabelPaint: Paint = Paint()

    init{
        healthPaint.color = ContextCompat.getColor(context, R.color.green)

        healthLabelPaint.color = ContextCompat.getColor(context, R.color.white)
        healthLabelPaint.textAlign = Paint.Align.CENTER
        healthLabelPaint.textSize = screenDimensions.y / 30.0f
    }

    fun draw(canvas: Canvas, currentHealth: Int, maxHealth: Int){
        val healthBarStopPos = -borderBottom / maxHealth * currentHealth + borderBottom
        canvas.drawRect(0.0f, healthBarStopPos, screenDimensions.x / 2.0f, borderBottom, healthPaint)
        canvas.drawText("$currentHealth", screenDimensions.x / 4.5f, borderBottom - borderBottom / 1.7f, healthLabelPaint)
        canvas.drawText("/ $maxHealth", screenDimensions.x / 4.5f, borderBottom - borderBottom / 2.7f, healthLabelPaint)
    }
}