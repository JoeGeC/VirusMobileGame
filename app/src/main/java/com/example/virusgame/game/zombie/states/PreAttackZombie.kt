package com.example.virusgame.game.zombie.states

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.content.ContextCompat
import com.example.virusgame.R
import com.example.virusgame.SoundManager
import com.example.virusgame.vibrator.Vibrator
import com.example.virusgame.clock.Clock
import com.example.virusgame.game.events.ZombieAttackEvent
import com.example.virusgame.game.zombie.types.Zombie
import com.example.virusgame.game.zombie.animations.NormalZombieAnimations

class PreAttackZombie(var zombie: Zombie) : ZombieState(zombie) {
    override val animation: List<Bitmap> = zombie.animations.preAttackAnimation()
    internal var startTime: Long = System.nanoTime()
    private var shakeHealth: Int = zombie.maxHealth
    private val zombieAttackMeterPaint: Paint = Paint()
    private var vibrator = Vibrator(zombie.context)

    init {
        warningVibrate()
        zombieAttackMeterPaint.color = ContextCompat.getColor(zombie.context, R.color.blue)
        SoundManager.playSfx(zombie.context, R.raw.zombie_attack)
    }

    internal fun warningVibrate() {
        vibrator.vibrate(longArrayOf(0, 200, 200), 0)
    }

    override fun draw(canvas: Canvas, x: Float, y: Float) {
        super.draw(canvas, x, y)
        zombie.drawHealth(canvas)
        canvas.drawRect(zombie.getBarRect(zombie.maxHealth, shakeHealth, zombie.healthBarYOffset + 20), zombieAttackMeterPaint)
    }

    override fun onAnimationFinish() {
        frameNum = animation.size - 1
    }

    override fun onSuccessfulSwipe() { }

    override fun update() {
        if(zombie.active && Clock.haveMillisecondsPassedSince(startTime, zombie.attackTime)){
            vibrator.vibrate(600)
            zombie.setNextAttackTime()
            zombie.state = AttackZombie(zombie)
        }
    }

    fun onShake(damage: Int){
        takeShakeDamage(damage)
    }

    private fun takeShakeDamage(damage: Int) {
        shakeHealth -= damage
        if(shakeHealth <= 0){
            shakeHealth = 0
            successfulDefense()
        }
    }

    private fun successfulDefense() {
        vibrator.stop()
        zombie.state = AliveZombie(zombie)
        ZombieAttackEvent.onComplete()
    }

    fun finalize(){
        vibrator.stop()
    }

    fun pause() {
        vibrator.stop()
    }
}
