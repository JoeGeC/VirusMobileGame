package com.example.virusgame.game.zombie

import android.graphics.Bitmap
import android.graphics.Canvas

interface ZombieState {
    var lastFrameUpdateTime: Long
    val animation: List<Bitmap>
    fun getAnimationFrame(): Bitmap
    fun draw(canvas: Canvas, x: Float, y: Float)
    fun onSuccessfulSwipe()
    fun update()
}
