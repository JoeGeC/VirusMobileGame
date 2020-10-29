package com.example.virusgame

import android.graphics.Rect
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.virusgame.game.Speech
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.junit.Before

@RunWith(AndroidJUnit4::class)
class SpeechShould {
    private val context = InstrumentationRegistry.getInstrumentation().targetContext
    private val oneLineMessage = "Hello!"
    private val speech = Speech(context)

    @Before
    fun setup(){
        speech.lineLength = 10
    }

    @Test
    fun useAppContext() {
        assertEquals("com.example.virusgame", context.packageName)
    }

    @Test
    fun setMessageWhenUnderLineLength(){
        speech.setSpeechText(oneLineMessage)
        assertEquals(oneLineMessage, speech.messageToDisplay[0])
    }

    @Test
    fun activateWhenMessageSet(){
        speech.setSpeechText(oneLineMessage)
        assertEquals(true, speech.active)
    }

    @Test
    fun setMessageOnSeparateLinesWhenMessageOverLineLengthAndWordsDontSplitAcrossLines(){
        speech.setSpeechText("Hello my name is Joe and I'm great!")
        assertEquals("Hello my ", speech.messageToDisplay[0])
        assertEquals("name is ", speech.messageToDisplay[1])
        assertEquals("Joe and ", speech.messageToDisplay[2])
        assertEquals("I'm great! ", speech.messageToDisplay[3])
    }

    @Test
    fun deactivateWhenSpeechTouched(){
        speech.speechBubbleRect = Rect(0, 0, 10, 10)
        speech.onTouch(5, 5)
        assertEquals(false, speech.active)
    }
}