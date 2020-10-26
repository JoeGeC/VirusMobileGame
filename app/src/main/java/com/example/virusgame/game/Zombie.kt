package com.example.virusgame.game

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import androidx.core.content.ContextCompat
import androidx.core.content.contentValuesOf
import com.example.virusgame.R
import java.lang.Exception

class Zombie(private var images: List<Bitmap>, var context: Context, var deathHandler: ZombieDeathHandler) : SwipeTaker {
    private var screenWidth = Resources.getSystem().displayMetrics.widthPixels
    private var screenHeight = Resources.getSystem().displayMetrics.heightPixels
    private var x: Int = screenWidth / 2 - images[0].width / 2
    private var y: Int = screenHeight / 2 - images[0].height / 2
    override var rect: Rect get() { return Rect(x, y, x + images[0].width, y + images[0].height) } set(value) {}
    private val zombieHealthPaint: Paint = Paint()
    private var maxHealth = 3
    private var currentHealth = maxHealth
    private var gold = 5
    private var frameNum : Int = 0
    private var lastFrameUpdateTime: Long = 0

    init {
        zombieHealthPaint.color = ContextCompat.getColor(context, R.color.red)
        zombieHealthPaint.strokeWidth = 20.0f
    }

    fun draw(canvas: Canvas){
        canvas.drawBitmap(getAnimationFrame(), x.toFloat(), y.toFloat(), null)
        drawHealthBar(canvas)
    }

    private fun getAnimationFrame(): Bitmap {
        if((System.nanoTime() - lastFrameUpdateTime) / 1000000 > 100){
            lastFrameUpdateTime = System.nanoTime()
            frameNum++
            if(frameNum > images.size - 1) frameNum = 0
        }
        return images[frameNum]
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
        deathHandler.takeGold(gold)
        deathHandler.spawnNewZombie()
    }

    override fun onSuccessfulSwipe() {
        takeDamage(1)
    }
}
