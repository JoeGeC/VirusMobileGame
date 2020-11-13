package com.example.virusgame.game

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.virusgame.R
import com.example.virusgame.ScreenDimensions
import com.example.virusgame.SoundManager
import com.example.virusgame.clock.Clock
import com.example.virusgame.game.vector2.IntVector2

class Speech(private var context: Context) {
    private val sprite: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.speech_bubble)
    private val x : Int = ScreenDimensions.width / 2 - sprite.width / 2
    private var y : Int = (ScreenDimensions.height - sprite.height * 1.3f).toInt()
    private var speechBubbleRect = Rect((x + sprite.width / 2.5f).toInt(), y + sprite.height / 4, x + sprite.width, y + sprite.height)
    var fullRect = Rect(x, y, x + sprite.width, y + sprite.height)
    var lineLength = 23
    private val amountOfLines = 4
    private val textSize = ScreenDimensions.height / 60.0f
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
        tapPaint.textSize = ScreenDimensions.height / 100.0f
        tapPaint.typeface =  ResourcesCompat.getFont(context, R.font.unispace)
        tapPaint.textAlign = Paint.Align.RIGHT
    }

    fun draw(canvas: Canvas){
        if(!active) return
        canvas.drawBitmap(sprite, x.toFloat(), y.toFloat(), null)
        canvas.drawText(context.getString(R.string.tap), speechBubbleRect.right.toFloat() - speechBubbleRect.width() / 15, speechBubbleRect.top.toFloat(), tapPaint)
        canvas.drawText(context.getString(R.string.karl), speechBubbleRect.left.toFloat(), speechBubbleRect.top.toFloat(), namePaint)
        for(i in 0..3) drawLine(canvas, i)
    }

    private fun drawLine(canvas: Canvas, lineNum: Int){
        canvas.drawText(typedLine(lineNum), speechBubbleRect.left.toFloat(), linePosY[lineNum], speechPaint)
    }

    private fun typedLine(lineNum: Int): String{
        if(lineNum >= messageToDisplay.size) messageTyped[lineNum] = true
        if(messageSetTime[lineNum] < messageSetTime[0] || lineNum >= messageToDisplay.size) return ""
        if(messageTyped[lineNum]) return messageToDisplay[lineNum]
        if(Clock.haveMillisecondsPassedSince(messageSetTime[lineNum], 200))
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

    fun onTouch(touchPos: IntVector2) {
        if(touchPos.isInside(fullRect))
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
        else dismissMessage()
    }

    private fun dismissMessage() {
        active = false
    }

    fun skipMessageTyping() {
        messageSetTime = longArrayOf(0, 0, 0, 0, 0)
        currentChar = intArrayOf(getLineLength(0), getLineLength(1), getLineLength(2), getLineLength(3), 0)
        messageTyped = booleanArrayOf(true, true, true, true)
    }

    private fun getLineLength(lineNum: Int): Int {
        if(messageToDisplay.size <= lineNum) return 0
        return messageToDisplay[lineNum].length
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