package com.example.virusgame.game.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.virusgame.R
import com.example.virusgame.ScreenDimensions
import com.example.virusgame.game.vector2.FloatVector2

class ZombieHeartUi(context: Context, borderBottom: Float) {
    private val paint: Paint = Paint()
    private val heartBorder: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.zombie_heart_border)
    private val heartBorderPos: FloatVector2 = FloatVector2(
        ScreenDimensions.width / 1.55f,
        borderBottom - borderBottom / 1.5f - heartBorder.height
    )
    private val heartsNumberPos: FloatVector2 = FloatVector2(
        heartBorderPos.x + heartBorder.width / 1.6f,
        heartBorderPos.y + heartBorder.height / 1.5f
    )

    init{
        paint.color = ContextCompat.getColor(context, R.color.white)
        paint.textSize = ScreenDimensions.height / 40.0f
        paint.typeface = ResourcesCompat.getFont(context, R.font.unispace)
        paint.textAlign = Paint.Align.CENTER
    }

    fun draw(canvas: Canvas, bossHearts: Int){
        canvas.drawBitmap(heartBorder, heartBorderPos.x, heartBorderPos.y, null)
        canvas.drawText(bossHearts.toString(), heartsNumberPos.x, heartsNumberPos.y, paint)
    }
}