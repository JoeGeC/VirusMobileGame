package com.example.virusgame.game.zombie

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import androidx.core.content.ContextCompat
import com.example.virusgame.R
import com.example.virusgame.game.EntityHandler
import com.example.virusgame.game.SwipeTaker
import com.example.virusgame.game.vector2.FloatVector2
import kotlin.math.pow
import kotlin.random.Random

class Zombie(var context: Context, var entityHandler: EntityHandler, var rectOffset: FloatVector2) : SwipeTaker {
    var active: Boolean = true
    internal var state: ZombieState = AliveZombie(this)
    private var screenWidth = Resources.getSystem().displayMetrics.widthPixels
    private var screenHeight = Resources.getSystem().displayMetrics.heightPixels
    private var x: Int = screenWidth / 2 - state.animation[0].width / 2
    private var y: Int = (screenHeight / 1.8f - state.animation[0].height / 2).toInt()

    private val zombieHealthPaint: Paint = Paint()
    private var maxHealth = 0
    private var currentHealth = maxHealth
    internal var gold = 5
    internal var attack = 1
    internal var canAttack = false
    internal var attackSpeed = 3000
    internal var attackTime: Int = 3000
    internal var lastAttackTime: Long = 0

    private var fullRect: Rect get(){
        return Rect(x, y, x + state.animation[0].width, y + state.animation[0].height)
    } set(value) {}

    override var rect: Rect get() {
        return Rect(
            (x + fullRect.width() / 5 + rectOffset.x).toInt(),
            (y + fullRect.height() / 5 + rectOffset.y).toInt(),
            (x + fullRect.width() - fullRect.width() / 5 + rectOffset.x).toInt(),
            (y + fullRect.height() - fullRect.height() / 5 + rectOffset.y).toInt())
    } set(value) {}

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
            state = DeadZombie(this)
        }
    }

    override fun onSuccessfulSwipe() {
        state.onSuccessfulSwipe()
    }

    fun update() {
        if(active) state.update()
    }

    internal fun drawHealthBar(canvas: Canvas) {
        val healthBarStartPos = rect.left - 10.0f
        val maxHealthBarStopPos = rect.right + 10.0f
        val healthBarStopPos: Float = try {
            (maxHealthBarStopPos - healthBarStartPos) / maxHealth * currentHealth + healthBarStartPos
        } catch (e: Exception) {
            healthBarStartPos
        }
        val healthBarHeight = y - 50.0f
        canvas.drawLine(healthBarStartPos, healthBarHeight, healthBarStopPos, healthBarHeight, zombieHealthPaint)
    }

    fun setStats(wave: Int, playerStrength: Int){
        val healthVal = wave.toFloat().pow(1.5f) * 3
        maxHealth = Random.nextInt((healthVal * 0.8).toInt(), (healthVal * 1.2).toInt())
        currentHealth = maxHealth
        gold = maxHealth
        val attackVal = wave.toFloat().pow(1.5f) + 1
        attack = Random.nextInt((attackVal * 0.8).toInt(), (attackVal * 1.2).toInt())
        canAttack = wave > 1
        attackSpeed = (playerStrength * 300) / wave
        setNextAttackTime()
        lastAttackTime = System.nanoTime()
    }

    fun setNextAttackTime(){
        attackTime = Random.nextInt((attackSpeed * 0.8).toInt(), (attackSpeed * 1.2).toInt())
    }
}
