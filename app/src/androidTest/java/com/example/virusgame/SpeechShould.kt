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
    private val message = "Hello world!"
    private val speech = Speech(context)

    @Before
    fun setup(){
        speech.setSpeechText(message)
    }

    @Test
    fun useAppContext() {
        assertEquals("com.example.virusgame", context.packageName)
    }

    @Test
    fun setMessageWhenUnderLineLength(){
        assertEquals(message, speech.messageToDisplay[0])
    }

    @Test
    fun activateWhenMessageSet(){
        assertEquals(true, speech.active)
    }
}