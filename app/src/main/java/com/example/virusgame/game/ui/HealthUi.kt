package com.example.virusgame.game.ui

import android.content.Context
import android.graphics.*
import androidx.core.content.ContextCompat
import com.example.virusgame.R
import com.example.virusgame.game.vector2.FloatVector2

class HealthUi (context: Context, private val screenDimensions: FloatVector2, private val borderBottom: Float){
    private val healthPaint: Paint = Paint()
    private val healthLabelPaint: Paint = Paint()
    private val healthBorder: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.health_border)
    private val healthBorderPos: FloatVector2 = FloatVector2(screenDimensions.x / 1.55f, borderBottom - borderBottom / 2.4f - healthBorder.height)
    private val healthRect: Rect = Rect(
        (healthBorderPos.x + healthBorder.width / 2.7f).toInt(),
        (healthBorderPos.y + healthBorder.height / 4.0f).toInt(),
        (healthBorderPos.x + healthBorder.width / 1.12f).toInt(),
        (healthBorderPos.y + healthBorder.height / 1.4f).toInt()
    )

    init{
        healthPaint.color = ContextCompat.getColor(context, R.color.green)

        healthLabelPaint.color = ContextCompat.getColor(context, R.color.white)
        healthLabelPaint.textAlign = Paint.Align.CENTER
        healthLabelPaint.textSize = screenDimensions.y / 30.0f
    }

    fun draw(canvas: Canvas, currentHealth: Int, maxHealth: Int){
        canvas.drawBitmap(healthBorder, healthBorderPos.x, healthBorderPos.y, null)
        val healthBarStopPos = ((healthRect.right - healthRect.left) / maxHealth * currentHealth + healthRect.left).toFloat()
        canvas.drawRect(healthRect.left.toFloat(), healthRect.top.toFloat(), healthBarStopPos, healthRect.bottom.toFloat(), healthPaint)
    }
}