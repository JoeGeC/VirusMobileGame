package com.example.virusgame.game.zombie.animations

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory

abstract class ZombieAnimations(private val context: Context) {
    abstract fun idleAnimation(): List<Bitmap>
    abstract fun dieAnimation(): List<Bitmap>
    abstract fun preAttackAnimation(): List<Bitmap>
    abstract fun attackAnimation(): List<Bitmap>
    abstract fun hurtAnimation(): List<Bitmap>

    protected fun getResource(resource: Int): Bitmap {
        return BitmapFactory.decodeResource(context.resources, resource)
    }
}
