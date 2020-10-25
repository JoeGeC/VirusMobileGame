package com.example.virusgame

import android.graphics.Rect

interface SwipeTaker {
    fun onSuccessfulSwipe()
    var rect: Rect
}
