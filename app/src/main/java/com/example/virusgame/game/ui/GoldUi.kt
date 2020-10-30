package com.example.virusgame.game.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.content.ContextCompat
import com.example.virusgame.R
import com.example.virusgame.game.Vector2

class GoldUi(context: Context, private var screenDimensions: Vector2, private var borderBottom: Float) {
    private val paint: Paint = Paint()

    init{
        paint.color = ContextCompat.getColor(context, R.color.gold)
        paint.textSize = screenDimensions.y / 30.0f
    }

    fun draw(canvas: Canvas, gold: Int){
        canvas.drawText("Gold: $gold", screenDimensions.x / 1.5f, borderBottom - borderBottom / 20.0f, paint)
    }
}
