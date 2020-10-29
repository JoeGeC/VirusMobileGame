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
    private var speechBubbleRect = Rect((x + sprite.width / 2.5f).toInt(), y + sprite.height / 4, x + sprite.width, y + sprite.height)
    var fullRect = Rect(x, y, x + sprite.width, y + sprite.height)
    var lineLength = 23
    private val amountOfLines = 4
    private val textSize = screenHeight / 60.0f
    private val line1y = speechBubbleRect.top + speechBubbleRect.height() / 5.0f
    private val namePaint: Paint = Paint()
    private val speechPaint: Paint = Paint()
    var messageToDisplay = mutableListOf<String>()
    var active = false
    var currentChar = 1
    private var timeSpeechSet: Long = 0

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
        canvas.drawText(context.getString(R.string.knight), speechBubbleRect.left.toFloat(), speechBubbleRect.top.toFloat(), namePaint)
        drawLine(canvas, 1)
        drawLine(canvas, 2)
        drawLine(canvas, 3)
        drawLine(canvas, 4)
    }

    private fun drawLine(canvas: Canvas, lineNum: Int){
        canvas.drawText(typedLine(messageToDisplay[lineNum]), speechBubbleRect.left.toFloat(), line1y * (lineNum + 1), speechPaint)
    }

    fun typedLine(line: String): String{
        return "H"
    }

    fun onTouch(xTouch: Int, yTouch: Int) {
        if(xTouch > fullRect.left && xTouch < fullRect.right && yTouch > fullRect.top && yTouch < fullRect.bottom)
            displayNextPartOfMessage()
    }

    private fun displayNextPartOfMessage() {
        if(messageToDisplay.size > amountOfLines)
            repeat(amountOfLines){ messageToDisplay.removeAt(0) }
        else
            active = false
    }

    fun setSpeechText(message: String) {
        messageToDisplay = setMessageToDisplay(message)
        active = true
        timeSpeechSet = System.nanoTime()
    }

    private fun setMessageToDisplay(message: String): MutableList<String> {
        if (message.length < lineLength) return mutableListOf(message)
        val result = mutableListOf<String>()
        val splitMessage = message.split(" ")
        var currentLine = ""
        splitMessage.forEachIndexed { i, word ->
            currentLine += "$word "
            if(atEndOfMessage(i, splitMessage) || nextWordWillGoOverLineLength(currentLine, splitMessage[i + 1])){
                result.add(currentLine)
                currentLine = ""
            }
        }
        return result
    }

    private fun nextWordWillGoOverLineLength(currentLine: String, nextWord: String) = currentLine.length + nextWord.length > lineLength

    private fun atEndOfMessage(i: Int, splitMessage: List<String>) = i == splitMessage.size - 1
}