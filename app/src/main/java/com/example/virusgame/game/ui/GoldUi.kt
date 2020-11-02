package com.example.virusgame.game.ui

import android.content.Context
import android.graphics.*
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.virusgame.R
import com.example.virusgame.game.vector2.FloatVector2

class GoldUi(context: Context, screenDimensions: FloatVector2, borderBottom: Float) {
    private val paint: Paint = Paint()
    private val goldBorder: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.gold_border)
    private val goldBorderPos: FloatVector2 = FloatVector2(
        screenDimensions.x / 1.55f,
        borderBottom - borderBottom / 18.0f - goldBorder.height
    )
    private val goldPos: FloatVector2 = FloatVector2(
        goldBorderPos.x + goldBorder.width / 1.6f,
        goldBorderPos.y + goldBorder.height / 1.5f
    )

    init{
        paint.color = ContextCompat.getColor(context, R.color.white)
        paint.textSize = screenDimensions.y / 40.0f
        paint.typeface = ResourcesCompat.getFont(context, R.font.unispace)
        paint.textAlign = Paint.Align.CENTER
    }

    fun draw(canvas: Canvas, gold: Int){
        canvas.drawBitmap(goldBorder, goldBorderPos.x, goldBorderPos.y, null)
        canvas.drawText(gold.toString(), goldPos.x, goldPos.y, paint)
    }
}
