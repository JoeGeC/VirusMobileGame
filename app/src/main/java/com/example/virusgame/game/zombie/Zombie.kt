package com.example.virusgame.game.zombie

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import androidx.core.content.ContextCompat
import com.example.virusgame.R
import com.example.virusgame.ScreenDimensions
import com.example.virusgame.SoundManager
import com.example.virusgame.game.EntityHandler
import com.example.virusgame.game.SwipeTaker
import com.example.virusgame.game.vector2.FloatVector2
import com.example.virusgame.game.vector2.IntVector2
import com.example.virusgame.game.zombie.states.AliveZombie
import com.example.virusgame.game.zombie.states.DeadZombie
import com.example.virusgame.game.zombie.states.PreAttackZombie
import com.example.virusgame.game.zombie.states.ZombieState
import kotlin.math.pow
import kotlin.random.Random

open class Zombie(var context: Context, var entityHandler: EntityHandler, private var rectOffset: IntVector2) : AzimuthEntity(), SwipeTaker {
    var active: Boolean = true
    internal var state: ZombieState = AliveZombie(this)
    override var position = FloatVector2(0f, ScreenDimensions.height / 1.4f - state.animation[0].height)
    internal val healthBarYOffset = 30

    private val zombieHealthPaint: Paint = Paint()
    internal var maxHealth = 0
    protected var currentHealth = maxHealth
    internal var gold = 5
    internal var hearts = 0
    internal var attack = 1
    internal var canAttack = false
    internal var attackSpeed = 3000
    internal var attackTime: Int = 3000
    internal var lastAttackTime: Long = 0
    private var deactivatedTime: Long = 0
    open val healthBarOffset: Int = 100

    protected var fullRect: Rect get(){
        return Rect(position.x.toInt(), position.y.toInt(), position.x.toInt() + state.animation[0].width, position.y.toInt() + state.animation[0].height)
    } set(value) {}

    override var hitRect: Rect get() {
        return Rect(
            (position.x + fullRect.width() / 4  - rectOffset.x).toInt(),
            (position.y + fullRect.height() / 5 + rectOffset.y).toInt(),
            (position.x + fullRect.width() - fullRect.width() / 5 - rectOffset.x).toInt(),
            (position.y + fullRect.height() - fullRect.height() / 5 + rectOffset.y).toInt())
    } set(value) {}

    init {
        zombieHealthPaint.color = ContextCompat.getColor(context, R.color.red)
        SoundManager.playRandomSfxOf(context, listOf(R.raw.zombie, R.raw.zombie2, R.raw.zombie3, R.raw.zombie4, R.raw.zombie5, R.raw.zombie6, R.raw.zombie7))
    }

    open fun draw(canvas: Canvas){
        state.draw(canvas, position.x, position.y)
    }

    internal fun takeDamage(damage: Int) {
        if(active){
            currentHealth -= damage
            SoundManager.playSfx(context, R.raw.sword)
        }
        if(currentHealth <= 0) die()
    }

    protected open fun die() {
        currentHealth = 0
        state = DeadZombie(this)
    }

    override fun onSuccessfulSwipe() {
        state.onSuccessfulSwipe()
    }

    fun update(azimuth: Double) {
        if(active) state.update()
        setPositionOnScreen(azimuth)
    }

    fun drawHealth(canvas: Canvas){
        canvas.drawRect(getBarRect(maxHealth, currentHealth, healthBarYOffset), zombieHealthPaint)
    }

    internal fun getBarRect(maxValue: Int, currentValue: Int,  yOffset: Int) : Rect {
        val startPos = fullRect.left + healthBarOffset
        val endPos = fullRect.right - healthBarOffset
        val currentEndPos: Int = try {
            (endPos - startPos) / maxValue * currentValue + startPos
        } catch (e: Exception) {
            startPos
        }
        val bottom = position.y.toInt() - yOffset
        val top = bottom - 20
        return Rect(startPos, top, currentEndPos, bottom)
    }

    open fun setStats(wave: Int, playerStrength: Int){
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
        deactivatedTime = System.nanoTime()
        setLocation()
    }

    fun setNextAttackTime(){
        attackTime = Random.nextInt((attackSpeed * 0.8).toInt(), (attackSpeed * 1.2).toInt())
    }

    private fun adjustTimesForDeactivation() {
        val offsetTime = System.nanoTime() - deactivatedTime
        lastAttackTime += offsetTime
        state.lastFrameUpdateTime += offsetTime
        if(state is PreAttackZombie) (state as PreAttackZombie).startTime += offsetTime
    }

    fun onShake(damage: Int) {
        if(state is PreAttackZombie) (state as PreAttackZombie).onShake(damage)
    }

    fun deactivate() {
        active = false
        deactivatedTime = System.nanoTime()
        if(state is PreAttackZombie) (state as PreAttackZombie).pause()
    }

    fun resume() {
        if(active) return
        active = true
        adjustTimesForDeactivation()
        if(state is PreAttackZombie) (state as PreAttackZombie).warningVibrate()
    }
}
