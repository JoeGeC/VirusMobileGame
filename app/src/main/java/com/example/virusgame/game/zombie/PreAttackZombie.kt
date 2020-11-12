package com.example.virusgame.game.zombie

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.core.content.ContextCompat
import com.example.virusgame.R
import com.example.virusgame.Vibrator
import com.example.virusgame.clock.Clock
import com.example.virusgame.game.ShakeSensor
import com.example.virusgame.game.events.FirstDefenceEvent
import com.example.virusgame.game.events.ZombieAttackEvent

class PreAttackZombie(var zombie: Zombie) : ZombieState {
    private var frameNum: Int = 0
    private var lastFrameUpdateTime: Long = 0
    override val animation: List<Bitmap> = ZombieAnimations(zombie.context).preAttackAnimation1()
    private val startTime: Long = System.nanoTime()
    private var shakeHealth: Int = zombie.maxHealth
    private val zombieAttackMeterPaint: Paint = Paint()

    private lateinit var sensorManager: SensorManager
    private lateinit var accelerometer: Sensor
    private var shakeSensor: ShakeSensor = ShakeSensor()

    init {
        setupShakeSensor()
        Vibrator(zombie.context).vibrate(400)
        zombieAttackMeterPaint.color = ContextCompat.getColor(zombie.context, R.color.blue)
    }

    private fun setupShakeSensor() {
        sensorManager = zombie.context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer  = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        shakeSensor.setOnShakeListener(object : ShakeSensor.OnShakeListener {
            override fun onShake() {
                sensorManager.unregisterListener(shakeSensor)
                zombie.state = AliveZombie(zombie)
                ZombieAttackEvent.complete = true
                FirstDefenceEvent.trigger()
            }
        })
        sensorManager.registerListener(shakeSensor, accelerometer, SensorManager.SENSOR_DELAY_UI)
    }

    override fun draw(canvas: Canvas, x: Float, y: Float) {
        canvas.drawBitmap(getAnimationFrame(), x, y, null)
        zombie.drawHealth(canvas)
        canvas.drawRect(zombie.getBarRect(shakeHealth, zombie.maxHealth, zombie.healthBarYOffset + 20), zombieAttackMeterPaint)
    }

    override fun getAnimationFrame(): Bitmap {
        if(Clock.haveMillisecondsPassedSince(lastFrameUpdateTime, 100)){
            lastFrameUpdateTime = System.nanoTime()
            frameNum++
            if(frameNum > animation.size - 1) frameNum = animation.size - 1
        }
        return animation[frameNum]
    }

    override fun onSuccessfulSwipe() { }

    override fun update() {
        if(Clock.haveMillisecondsPassedSince(startTime, zombie.attackTime)){
            sensorManager.unregisterListener(shakeSensor)
            zombie.setNextAttackTime()
            zombie.state = AttackZombie(zombie)
        }
    }
}
