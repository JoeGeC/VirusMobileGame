package com.example.virusgame.game

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.content.ContextCompat
import com.example.virusgame.R

class Player(var context: Context){
    private var screenWidth = Resources.getSystem().displayMetrics.widthPixels
    var gold: Int = 0
    var health: Int = 10
    private val goldPaint: Paint = Paint()
    private val healthPaint: Paint = Paint()

    init {
        setPaintParams(goldPaint,50.0f, R.color.gold)
        setPaintParams(healthPaint, 50.0f, R.color.green)
    }

    private fun setPaintParams(paint: Paint, textSize: Float, colorResource: Int) {
        paint.textSize = textSize
        paint.color = ContextCompat.getColor(context, colorResource)
    }

    fun draw(canvas: Canvas){
        canvas.drawText("Gold: $gold", screenWidth - 250.0f, 150.0f, goldPaint)
        canvas.drawText("Health: $health", screenWidth - 250.0f, 100.0f, healthPaint)
    }

    fun increaseGold(goldToAdd: Int){
        gold += goldToAdd
    }
}