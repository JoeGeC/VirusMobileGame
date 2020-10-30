package com.example.virusgame.game.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.content.ContextCompat
import com.example.virusgame.R
import com.example.virusgame.game.Vector2

class WaveUi (context: Context, private val screenDimensions: Vector2, private val borderBottom: Float) {
    private val waveLabelPaint: Paint = Paint()
    private val wavePaint: Paint = Paint()

    init {
        waveLabelPaint.color = ContextCompat.getColor(context, R.color.white)
        waveLabelPaint.textAlign = Paint.Align.CENTER
        waveLabelPaint.textSize = screenDimensions.y / 50.0f

        wavePaint.color = ContextCompat.getColor(context, R.color.white)
        wavePaint.textAlign = Paint.Align.CENTER
        wavePaint.textSize = screenDimensions.x / 10.0f
    }

    fun draw(canvas: Canvas, wave: Int){
        canvas.drawText("Wave", screenDimensions.x / 2.0f, borderBottom - screenDimensions.y / 55.0f, waveLabelPaint)
        canvas.drawText(wave.toString(), screenDimensions.x / 2.0f, borderBottom + screenDimensions.y / 15.0f, wavePaint)
    }
}