package com.example.virusgame

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
    private val twoLineMessage = "Hello world!"
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
    fun setMessageOnTwoLinesWhenMessageOverLineLengthAndWordsDontSplitAcrossLines(){
        speech.setSpeechText(twoLineMessage)
        assertEquals("Hello", speech.messageToDisplay[0])
        assertEquals("world!", speech.messageToDisplay[1])
    }
}