package com.example.virusgame.game.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import com.example.virusgame.R
import com.example.virusgame.game.vector2.FloatVector2

class BorderUi(context: Context, private val screenDimensions: FloatVector2) {
    private val border: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.ui_border)
    private val borderPosY = screenDimensions.y / 10.0f * 3.2f - border.height
    val bottom = borderPosY + border.height - border.height / 10.0f * 1.922f

    fun draw(canvas: Canvas){
        canvas.drawBitmap(border, screenDimensions.x / 2.0f - border.width / 2.0f, borderPosY, null)
    }
}