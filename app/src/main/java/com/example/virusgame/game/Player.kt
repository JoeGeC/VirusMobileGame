package com.example.virusgame.game

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.content.ContextCompat
import com.example.virusgame.R

class Player(context: Context){
    private var screenWidth = Resources.getSystem().displayMetrics.widthPixels
    var gold: Int = 0
    var health: Int = 10
    private val goldPaint: Paint = Paint()

    init {
        goldPaint.textSize = 40.0f
        goldPaint.color = ContextCompat.getColor(context, R.color.gold)
    }

    fun draw(canvas: Canvas){
        canvas.drawText("Gold: $gold", screenWidth - 250.0f, 150.0f, goldPaint)
    }
}