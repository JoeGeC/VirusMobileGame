package viruswiper.game.zombie.types

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import androidx.core.content.ContextCompat
import viruswiper.R
import viruswiper.ScreenDimensions
import viruswiper.SoundManager
import viruswiper.game.EntityHandler
import viruswiper.game.SwipeTaker
import viruswiper.game.rotation.AzimuthEntity
import viruswiper.game.vector2.FloatVector2
import viruswiper.game.zombie.animations.ZombieAnimations
import viruswiper.game.zombie.states.AliveZombie
import viruswiper.game.zombie.states.DeadZombie
import viruswiper.game.zombie.states.PreAttackZombie
import viruswiper.game.zombie.states.ZombieState
import kotlin.math.pow
import kotlin.random.Random

abstract class Zombie(var context: Context, var entityHandler: EntityHandler) : AzimuthEntity(),
    SwipeTaker {
    var active: Boolean = true
    abstract val animations: ZombieAnimations
    lateinit var state: ZombieState
    internal val healthBarYOffset = 30

    private val zombieHealthPaint: Paint = Paint()
    internal var maxHealth = 0
    protected var currentHealth = maxHealth
    internal var gold = 5
    internal var hearts = 0
    internal var attack = 1
    internal open var canAttack = true
    internal var attackSpeed = 3000
    internal var attackTime: Int = 3000
    internal var lastAttackTime: Long = 0
    private var deactivatedTime: Long = 0
    open val healthBarOffset: Int = 150

    protected var fullRect: Rect get(){
        return Rect(position.x.toInt(), position.y.toInt(), position.x.toInt() + state.animation[0].width, position.y.toInt() + state.animation[0].height)
    } set(value) {}

    private var hitRectLeftOffset = 0
    private var hitRectRightOffset = 0
    private var hitRectTopOffset = 0
    private var hitRectBottomOffset = 0
    override var hitRect: Rect get() {
        return Rect(
            (position.x + hitRectLeftOffset).toInt(),
            (position.y + hitRectTopOffset).toInt(),
            (position.x + hitRectRightOffset).toInt(),
            (position.y + hitRectBottomOffset).toInt())
    } set(value) {}

    init {
        zombieHealthPaint.color = ContextCompat.getColor(context, R.color.red)
        SoundManager.playRandomSfxOf(context, listOf(R.raw.zombie, R.raw.zombie2, R.raw.zombie3, R.raw.zombie4, R.raw.zombie5, R.raw.zombie6, R.raw.zombie7))
    }

    fun afterInit(){
        state = AliveZombie(this)
        position = FloatVector2(0f, ScreenDimensions.height / 1.3f - state.animation[0].height)
        hitRectLeftOffset = fullRect.width() / 4
        hitRectRightOffset = fullRect.width() - fullRect.width() / 5
        hitRectTopOffset = fullRect.height() / 5
        hitRectBottomOffset = fullRect.height() - fullRect.height() / 5
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

    open fun update(azimuth: Double) {
        if(active) state.update()
        setPositionOnScreen(azimuth)
    }

    fun drawHealth(canvas: Canvas){
        canvas.drawRect(getBarRect(maxHealth, currentHealth, healthBarYOffset), zombieHealthPaint)
    }

    //for health and energy bars
    internal fun getBarRect(maxValue: Int, currentValue: Int,  yOffset: Int) : Rect {
        val startPos = fullRect.left + healthBarOffset
        val endPos = fullRect.right - healthBarOffset * 3
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
        attackSpeed = setAttackSpeed(playerStrength, wave)
        attackTime = setNextAttackTime()
        lastAttackTime = System.nanoTime()
        deactivatedTime = System.nanoTime()
        setLocation()
    }

    open fun setNextAttackTime(): Int {
        return Random.nextInt((attackSpeed * 0.8).toInt(), (attackSpeed * 1.2).toInt())
    }

    open fun setAttackSpeed(playerStrength: Int, wave: Int): Int{
        return (playerStrength * 300) / wave
    }

    private fun adjustTimesFromDeactivation() {
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
        adjustTimesFromDeactivation()
        if(state is PreAttackZombie) (state as PreAttackZombie).warningVibrate()
    }
}
