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
    private val y : Int = (screenHeight - sprite.height * 1.3f).toInt()
    private val speechBubble = Rect((x + sprite.width / 2.5f).toInt(), y + sprite.height / 4, x + sprite.width, y + sprite.height)
    private val namePaint: Paint = Paint()
    private val speechPaint: Paint = Paint()

    init{
        namePaint.color = ContextCompat.getColor(context, R.color.blue)
        namePaint.textSize = screenHeight / 40.0f

        speechPaint.color = ContextCompat.getColor(context, R.color.white)
        speechPaint.textSize = screenHeight / 40.0f
        speechPaint.typeface = Typeface.createFromAsset(context.assets, "fonts/unispace")
    }

    fun draw(canvas: Canvas, speech: String){
        canvas.drawBitmap(sprite, x.toFloat(), y.toFloat(), null)
        canvas.drawText("Knight:", speechBubble.left.toFloat(), speechBubble.top.toFloat(), namePaint)
        canvas.drawText("WWWWWWWWWWWW", speechBubble.left.toFloat(), speechBubble.top + speechBubble.height() / 5.0f, speechPaint)
        canvas.drawText("iiiiiiiiiiii", speechBubble.left.toFloat(), speechBubble.top + speechBubble.height() / 5.0f * 2, speechPaint)
        canvas.drawText(speech, speechBubble.left.toFloat(), speechBubble.top + speechBubble.height() / 5.0f * 3, speechPaint)
        canvas.drawText(speech, speechBubble.left.toFloat(), speechBubble.top + speechBubble.height() / 5.0f * 4, speechPaint)
    }
}