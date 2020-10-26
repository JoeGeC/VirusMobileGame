package com.example.virusgame.game

import android.graphics.Bitmap
import android.graphics.Canvas

interface ZombieState {
    val animation: List<Bitmap>
    fun getAnimationFrame(): Bitmap
    fun drawHealthBar(canvas: Canvas)
}
