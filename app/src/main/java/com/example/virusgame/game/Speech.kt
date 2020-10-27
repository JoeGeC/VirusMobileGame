package com.example.virusgame.game

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import androidx.core.content.ContextCompat
import com.example.virusgame.R

class Speech(context: Context) {
    private val sprite: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.knight)
    private val screenHeight = Resources.getSystem().displayMetrics.heightPixels
    private val screenWidth = Resources.getSystem().displayMetrics.widthPixels
    private val x : Int = screenWidth / 2 - sprite.width / 2
    private val y : Int = screenHeight - 600
    private val speechBubble = Rect(x + 500, y + 110, x + sprite.width, y + sprite.height)
    private val namePaint: Paint = Paint()
    private val speechPaint: Paint = Paint()

    init{
        namePaint.textSize = 70.0f
        namePaint.color = ContextCompat.getColor(context, R.color.blue)

        speechPaint.textSize = 70.0f
        speechPaint.color = ContextCompat.getColor(context, R.color.white)
    }

    fun draw(canvas: Canvas, speech: String){
        canvas.drawBitmap(sprite, screenWidth / 2.0f - sprite.width / 2.0f, screenHeight - 600.0f, null)
        canvas.drawText("Knight:", speechBubble.left.toFloat(), speechBubble.top.toFloat(), namePaint)
        canvas.drawText(speech, speechBubble.left.toFloat(), speechBubble.top + 100.0f, speechPaint)
    }
}