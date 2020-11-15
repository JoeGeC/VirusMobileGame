package com.example.virusgame.game.doubleSwipe

import android.view.MotionEvent
import kotlin.math.abs

class DoubleSwipeManager(private var swipeHandler: DoubleSwipeHandler) {
    private var swipeMode = SwipeMode.NONE
    private var startX = 0f
    private var stopX = 0f

    companion object {
        private const val swipeLength = 100
    }

    fun startDoubleSwipe(event: MotionEvent) {
        swipeMode = SwipeMode.SWIPE
        startX = event.getX(0)
    }

    fun endDoubleSwipe() {
        swipeMode = SwipeMode.NONE
        if(swipeWasLongerThanSwipeLength())
            swipeHandler.onSuccessfulDoubleSwipe()
    }

    private fun swipeWasLongerThanSwipeLength() = abs(startX - stopX) > swipeLength

    fun moveDoubleSwipe(event: MotionEvent) {
        stopX = event.getX(0)
    }
}