package com.example.virusgame.game

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.content.ContextCompat
import com.example.virusgame.R

class Ui (context: Context){
    private val border: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.ui_border)
    private val screenWidth = Resources.getSystem().displayMetrics.widthPixels
    private val screenHeight = Resources.getSystem().displayMetrics.heightPixels
    private val borderPosY = screenHeight / 10.0f * 3.2f - border.height
    private val borderBottom = borderPosY + border.height - border.height / 10.0f * 1.922f
    private val waveLabelPaint: Paint = Paint()
    private val wavePaint: Paint = Paint()
    private val healthPaint: Paint = Paint()
    private val healthLabelPaint: Paint = Paint()
    private val goldPaint: Paint = Paint()
    private val levelPaint: Paint = Paint()

    init {
        waveLabelPaint.color = ContextCompat.getColor(context, R.color.white)
        waveLabelPaint.textAlign = Paint.Align.CENTER
        waveLabelPaint.textSize = screenHeight / 50.0f

        wavePaint.color = ContextCompat.getColor(context, R.color.white)
        wavePaint.textAlign = Paint.Align.CENTER
        wavePaint.textSize = screenHeight / 10.0f

        healthPaint.color = ContextCompat.getColor(context, R.color.green)

        healthLabelPaint.color = ContextCompat.getColor(context, R.color.white)
        healthLabelPaint.textAlign = Paint.Align.CENTER
        healthLabelPaint.textSize = screenHeight / 30.0f

        goldPaint.color = ContextCompat.getColor(context, R.color.gold)
        goldPaint.textSize = screenHeight / 30.0f

        levelPaint.color = ContextCompat.getColor(context, R.color.white)
        levelPaint.textSize = screenHeight / 30.0f
    }

    fun drawBorder(canvas: Canvas){
        canvas.drawBitmap(border, screenWidth / 2.0f - border.width / 2.0f, borderPosY, null)
    }

    fun drawWave(canvas: Canvas, wave: Int){
        canvas.drawText("Wave", screenWidth / 2.0f, borderBottom - screenHeight / 55.0f, waveLabelPaint)
        canvas.drawText(wave.toString(), screenWidth / 2.0f, borderBottom + screenHeight / 15.0f, wavePaint)
    }

    fun drawHealth(canvas: Canvas, currentHealth: Int, maxHealth: Int){
        val healthBarStopPos = -borderBottom / maxHealth * currentHealth + borderBottom
        canvas.drawRect(0.0f, healthBarStopPos, screenWidth / 2.0f, borderBottom, healthPaint)
        canvas.drawText("$currentHealth", screenWidth / 4.5f, borderBottom - borderBottom / 1.7f, healthLabelPaint)
        canvas.drawText("/ $maxHealth", screenWidth / 4.5f, borderBottom - borderBottom / 2.7f, healthLabelPaint)
    }

    fun drawGold(canvas: Canvas, gold: Int){
        canvas.drawText("Gold: $gold", screenWidth / 1.5f, borderBottom - borderBottom / 20.0f, goldPaint)
    }

    fun drawLevel(canvas: Canvas, level: Int){
        canvas.drawText("Level: $level", screenWidth / 1.5f, borderBottom - borderBottom / 4.0f, levelPaint)
    }
}