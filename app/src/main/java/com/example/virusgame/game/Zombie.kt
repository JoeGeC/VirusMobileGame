package com.example.virusgame.game

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import androidx.core.content.ContextCompat
import com.example.virusgame.R
import java.lang.Exception

class Zombie(var image: Bitmap, context: Context) : SwipeTaker {
    private var screenWidth = Resources.getSystem().displayMetrics.widthPixels
    private var screenHeight = Resources.getSystem().displayMetrics.heightPixels
    private var x: Int = screenWidth / 2 - image.width / 2
    private var y: Int = screenHeight / 2 - image.height / 2
    override var rect: Rect get() { return Rect(x, y, x + image.width, y + image.height) } set(value) {}
    private var maxHealth: Int = 3
    private var currentHealth: Int = maxHealth
    private val zombieHealthPaint: Paint = Paint()

    init {
        zombieHealthPaint.color = ContextCompat.getColor(context, R.color.red)
    }

    fun draw(canvas: Canvas){
        canvas.drawBitmap(image, x.toFloat(), y.toFloat(), null)
        drawHealthBar(canvas)
    }

    private fun drawHealthBar(canvas: Canvas) {
        val healthBarStartPos = x + 50.0f
        val maxHealthBarStopPos = rect.right - 50.0f
        val healthBarStopPos: Float = try {
            (maxHealthBarStopPos - healthBarStartPos) / maxHealth * currentHealth + healthBarStartPos
        } catch (e: Exception) {
            healthBarStartPos
        }
        val healthBarHeight = y - 50.0f
        canvas.drawLine(healthBarStartPos, healthBarHeight, healthBarStopPos, healthBarHeight, zombieHealthPaint)
    }

    private fun takeDamage(damage: Int) {
        currentHealth -= damage
        if(currentHealth <= 0){
            currentHealth = 0
            die()
        }
    }

    private fun die() {
    }

    override fun onSuccessfulSwipe() {
        takeDamage(1)
    }
}
