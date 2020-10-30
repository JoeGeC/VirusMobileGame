package com.example.virusgame.game.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.content.ContextCompat
import com.example.virusgame.R
import com.example.virusgame.game.Vector2

class LevelUi(context: Context, private var screenDimensions: Vector2, private var borderBottom: Float) {
    private val levelPaint: Paint = Paint()

    init {
        levelPaint.color = ContextCompat.getColor(context, R.color.white)
        levelPaint.textSize = screenDimensions.y / 30.0f
    }

    fun draw(canvas: Canvas, level: Int){
        canvas.drawText("Level: $level", screenDimensions.x / 1.5f, borderBottom - borderBottom / 4.0f, levelPaint)
    }
}
