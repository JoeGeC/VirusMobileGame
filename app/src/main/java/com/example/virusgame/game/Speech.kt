package com.example.virusgame.game

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.virusgame.Clock.Clock
import com.example.virusgame.MainActivity
import com.example.virusgame.R

object Speech {
    private val context: Context = MainActivity.applicationContext()
    private val sprite: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.knight)
    private val screenHeight = Resources.getSystem().displayMetrics.heightPixels
    private val screenWidth = Resources.getSystem().displayMetrics.widthPixels
    private val x : Int = screenWidth / 2 - sprite.width / 2
    private val y : Int = (screenHeight - sprite.height * 1.3f).toInt()
    private var speechBubbleRect = Rect((x + sprite.width / 2.5f).toInt(), y + sprite.height / 4, x + sprite.width, y + sprite.height)
    var fullRect = Rect(x, y, x + sprite.width, y + sprite.height)
    var lineLength = 23
    private const val amountOfLines = 4
    private val textSize = screenHeight / 60.0f
    private val linePosY = floatArrayOf(
        speechBubbleRect.top + speechBubbleRect.height() / 5.0f,
        speechBubbleRect.top + speechBubbleRect.height() / 5.0f * 2,
        speechBubbleRect.top + speechBubbleRect.height() / 5.0f * 3,
        speechBubbleRect.top + speechBubbleRect.height() / 5.0f * 4)
    var messageToDisplay = mutableListOf<String>()
    var active = false
    private var currentChar = intArrayOf(0, 0, 0, 0, 0)
    private var messageSetTime = longArrayOf(0, 0, 0, 0, 0)
    private var messageTyped = booleanArrayOf(false, false, false, false)
    private val namePaint: Paint = Paint()
    private val speechPaint: Paint = Paint()
    private val tapPaint: Paint = Paint()

    init{
        namePaint.color = ContextCompat.getColor(context, R.color.blue)
        namePaint.textSize = textSize
        namePaint.typeface =  ResourcesCompat.getFont(context, R.font.unispace)

        speechPaint.color = ContextCompat.getColor(context, R.color.white)
        speechPaint.textSize = textSize
        speechPaint.typeface =  ResourcesCompat.getFont(context, R.font.unispace)

        tapPaint.color = ContextCompat.getColor(context, R.color.white)
        tapPaint.textSize = screenHeight / 100.0f
        tapPaint.typeface =  ResourcesCompat.getFont(context, R.font.unispace)
        tapPaint.textAlign = Paint.Align.RIGHT
    }

    fun draw(canvas: Canvas){
        if(!active) return
        canvas.drawBitmap(sprite, x.toFloat(), y.toFloat(), null)
        canvas.drawText("[tap]", speechBubbleRect.right.toFloat() - speechBubbleRect.width() / 15, speechBubbleRect.top.toFloat(), tapPaint)
        canvas.drawText(context.getString(R.string.knight), speechBubbleRect.left.toFloat(), speechBubbleRect.top.toFloat(), namePaint)
        for(i in 0..3) drawLine(canvas, i)
    }

    private fun drawLine(canvas: Canvas, lineNum: Int){
        canvas.drawText(typedLine(lineNum), speechBubbleRect.left.toFloat(), linePosY[lineNum], speechPaint)
    }

    private fun typedLine(lineNum: Int): String{
        if(lineNum >= messageToDisplay.size) messageTyped[lineNum] = true
        if(messageSetTime[lineNum] < messageSetTime[0] || lineNum >= messageToDisplay.size) return ""
        if(messageTyped[lineNum]) return messageToDisplay[lineNum]
        if(Clock.millisecondsHavePassedSince(messageSetTime[lineNum], 200))
            typeNextCharacter(lineNum)
        var result = ""
        for(i in 0..currentChar[lineNum])
            result += messageToDisplay[lineNum][i]
        return result
    }

    private fun typeNextCharacter(lineNum: Int) {
        currentChar[lineNum]++
        if (currentChar[lineNum] >= messageToDisplay[lineNum].length) {
            messageSetTime[lineNum + 1] = System.nanoTime()
            messageTyped[lineNum] = true
        }
    }

    fun onTouch(xTouch: Int, yTouch: Int) {
        if(xTouch > fullRect.left && xTouch < fullRect.right && yTouch > fullRect.top && yTouch < fullRect.bottom)
            displayNextPartOfMessage()
    }

    private fun displayNextPartOfMessage() {
        if(!messageTyped[3]) {
            skipMessageTyping()
            return
        }
        resetMessageTyper()
        if(messageToDisplay.size > amountOfLines)
            repeat(amountOfLines){ messageToDisplay.removeAt(0) }
        else
            active = false
    }

    private fun skipMessageTyping() {
        messageSetTime = longArrayOf(0, 0, 0, 0, 0)
        currentChar = intArrayOf(messageToDisplay[0].length, messageToDisplay[1].length, messageToDisplay[2].length, messageToDisplay[3].length, 0)
        messageTyped = booleanArrayOf(true, true, true, true)
    }

    private fun resetMessageTyper() {
        messageSetTime[0] = System.nanoTime()
        currentChar = intArrayOf(0, 0, 0, 0, 0)
        messageTyped = booleanArrayOf(false, false, false, false)
    }

    fun setSpeechText(message: String) {
        resetMessageTyper()
        messageToDisplay = setMessageToDisplay(message)
        active = true
        messageSetTime[0] = System.nanoTime()
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