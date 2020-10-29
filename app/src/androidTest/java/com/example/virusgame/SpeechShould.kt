package com.example.virusgame

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.virusgame.game.Speech
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class SpeechShould {
    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun useAppContext() {
        assertEquals("com.example.virusgame", context.packageName)
    }

    @Test
    fun setMessageWhenUnderLineLength(){
        val message = "Hello world!"
        val speech = Speech(context)
        speech.setSpeechText(message)
        assertEquals(message, speech.messageToDisplay[0])
    }

    @Test
    fun activateWhenMessageSet(){
        val message = "Hello world!"
        val speech = Speech(context)
        speech.setSpeechText(message)
        assertEquals(true, speech.active)
    }
}