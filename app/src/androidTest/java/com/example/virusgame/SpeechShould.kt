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
    private val oneLineMessage = "test"
    private val twoLineMessage = "testtest"
    private val speech = Speech(context)

    @Before
    fun setup(){
        speech.lineLength = 4
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
    fun setMessageOnTwoLinesWhenMessageOverLineLength(){
        speech.setSpeechText(twoLineMessage)
        assertEquals("test", speech.messageToDisplay[0])
        assertEquals("test", speech.messageToDisplay[1])
    }
}