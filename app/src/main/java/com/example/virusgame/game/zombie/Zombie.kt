package com.example.virusgame.game.zombie

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import androidx.core.content.ContextCompat
import com.example.virusgame.R
import com.example.virusgame.game.SwipeTaker

class Zombie(var context: Context, var deathHandler: ZombieDeathHandler) : SwipeTaker {
    private var state: ZombieState = AliveZombie(this)
    private var screenWidth = Resources.getSystem().displayMetrics.widthPixels
    private var screenHeight = Resources.getSystem().displayMetrics.heightPixels
    internal var x: Int = screenWidth / 2 - state.animation[0].width / 2
    internal var y: Int = screenHeight / 2 - state.animation[0].height / 2

    private var fullRect: Rect get() { return Rect(x, y, x + state.animation[0].width, y + state.animation[0].height) } set(value) {}
    override var rect: Rect get() { return Rect(x + fullRect.width() / 5, y + fullRect.height() / 5, x + state.animation[0].width - fullRect.width() / 5, y + state.animation[0].height - fullRect.height() / 5) } set(value) {}
    internal val zombieHealthPaint: Paint = Paint()
    internal var maxHealth = 3
    internal var currentHealth = maxHealth
    internal var gold = 5

    init {
        zombieHealthPaint.color = ContextCompat.getColor(context, R.color.red)
        zombieHealthPaint.strokeWidth = 20.0f
    }

    fun draw(canvas: Canvas){
        state.draw(canvas, x.toFloat(), y.toFloat())
    }

    internal fun takeDamage(damage: Int) {
        currentHealth -= damage
        if(currentHealth <= 0){
            currentHealth = 0
            die()
        }
    }

    private fun die() {
        state = DeadZombie(this)
    }

    override fun onSuccessfulSwipe() {
        state.onSuccessfulSwipe()
    }
}
