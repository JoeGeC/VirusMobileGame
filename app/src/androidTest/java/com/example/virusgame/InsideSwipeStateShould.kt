package com.example.virusgame

import android.graphics.BitmapFactory
import android.graphics.Rect
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.virusgame.game.Zombie
import com.example.virusgame.game.swipestates.InsideSwipeState
import com.example.virusgame.game.swipestates.StartSwipeState
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InsideSwipeStateShould {
    private var sprite: Zombie? = null

    @Before
    fun setup(){
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        sprite = Zombie(BitmapFactory.decodeResource(appContext.resources, R.drawable.zombie))
        sprite!!.x = 0
        sprite!!.y = 0
        sprite!!.health = 10
    }

    @Test
    fun returnItselfIfStillTouchingInsideSprite() {
        val swipeState = InsideSwipeState(0, 0)
        assertEquals(swipeState, swipeState.onTouch(50, 50, sprite!!))
    }

    @Test
    fun returnStartSwipeStateIfNotStillTouchingInsideSprite(){
        val swipeState = InsideSwipeState(10, 10)
        assert(swipeState.onTouch(101, 101, sprite!!) is StartSwipeState)
    }

    @Test
    fun zombieTakesDamageWhenSuccessfulSwipe(){
        val swipeState = InsideSwipeState(10, 10)
        swipeState.onTouch(1000, 101, sprite!!)
        assertEquals(9, sprite!!.health)
    }
}