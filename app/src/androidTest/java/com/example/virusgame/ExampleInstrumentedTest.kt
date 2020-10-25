package com.example.virusgame

import android.graphics.BitmapFactory
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.virusgame.game.Zombie
import com.example.virusgame.game.swipestates.InsideSwipeState
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val swipeState = InsideSwipeState(500, 500)
        val sprite = Zombie(BitmapFactory.decodeResource(appContext.resources, R.drawable.zombie))
        assertEquals(swipeState.onTouch(501, 501, sprite), swipeState)
    }
}