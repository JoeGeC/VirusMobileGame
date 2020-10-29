package com.example.virusgame.game

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.virusgame.R

class Speech(private var context: Context) {
    private val sprite: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.knight)
    private val screenHeight = Resources.getSystem().displayMetrics.heightPixels
    private val screenWidth = Resources.getSystem().displayMetrics.widthPixels
    private val x : Int = screenWidth / 2 - sprite.width / 2
    private val y : Int = (screenHeight - sprite.height * 1.3f).toInt()
    private val speechBubble = Rect((x + sprite.width / 2.5f).toInt(), y + sprite.height / 4, x + sprite.width, y + sprite.height)
    private val fullRect = Rect(x, y, x + sprite.width, y + sprite.height)
    var lineLength = 23
    private val textSize = screenHeight / 60.0f
    private val line1y = speechBubble.top + speechBubble.height() / 5.0f
    private val line2y = speechBubble.top + speechBubble.height() / 5.0f * 2
    private val line3y = speechBubble.top + speechBubble.height() / 5.0f * 3
    private val line4y = speechBubble.top + speechBubble.height() / 5.0f * 4
    private val namePaint: Paint = Paint()
    private val speechPaint: Paint = Paint()
    var messageToDisplay = mutableListOf<String>()
    var active = false

    init{
        namePaint.color = ContextCompat.getColor(context, R.color.blue)
        namePaint.textSize = textSize
        namePaint.typeface =  ResourcesCompat.getFont(context, R.font.unispace)

        speechPaint.color = ContextCompat.getColor(context, R.color.white)
        speechPaint.textSize = textSize
        speechPaint.typeface =  ResourcesCompat.getFont(context, R.font.unispace)
    }

    fun draw(canvas: Canvas){
        if(!active) return
        canvas.drawBitmap(sprite, x.toFloat(), y.toFloat(), null)
        canvas.drawText(context.getString(R.string.knight), speechBubble.left.toFloat(), speechBubble.top.toFloat(), namePaint)
        canvas.drawText(messageToDisplay[0], speechBubble.left.toFloat(), line1y, speechPaint)
        canvas.drawText(messageToDisplay[1], speechBubble.left.toFloat(), line2y, speechPaint)
        canvas.drawText(messageToDisplay[2], speechBubble.left.toFloat(), line3y, speechPaint)
        canvas.drawText(messageToDisplay[3], speechBubble.left.toFloat(), line4y, speechPaint)
    }

    fun onTouch(xTouch: Int, yTouch: Int) {
        if(xTouch > fullRect.left && xTouch < fullRect.right && yTouch > fullRect.top && yTouch < fullRect.bottom)
            active = false
    }

    fun setSpeechText(message: String) {
        messageToDisplay = setMessageToDisplay(message)
        active = true
    }

    private fun setMessageToDisplay(message: String): MutableList<String> {
        if (message.length < lineLength) return mutableListOf(message)

        val result = mutableListOf<String>()
        val splitMessage = message.split(" ")
        var currentLine = ""
        splitMessage.forEachIndexed { i, word ->
            currentLine += word
            if(i == splitMessage.size - 1 || currentLine.length + splitMessage[i + 1].length > lineLength) {
                result.add(currentLine)
                currentLine = ""
            }
        }
        return result
    }
}