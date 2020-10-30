package com.example.virusgame.game.zombie

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.hardware.Sensor
import android.hardware.SensorManager
import com.example.virusgame.Clock.Clock
import com.example.virusgame.game.ShakeSensor
import com.example.virusgame.game.events.FirstDefenceEvent
import com.example.virusgame.game.events.ZombieAttackEvent

class PreAttackZombie(var zombie: Zombie) : ZombieState {
    private var frameNum: Int = 0
    private var lastFrameUpdateTime: Long = 0
    override val animation: List<Bitmap> = ZombieAnimations(zombie.context).preAttackAnimation1()
    private val startTime: Long = System.nanoTime()

    private lateinit var sensorManager: SensorManager
    private lateinit var accelerometer: Sensor
    private var shakeSensor: ShakeSensor = ShakeSensor()

    init {
        setupShakeSensor()
        sensorManager.registerListener(
            shakeSensor,
            accelerometer,
            SensorManager.SENSOR_DELAY_UI
        )
    }

    override fun draw(canvas: Canvas, x: Float, y: Float) {
        canvas.drawBitmap(getAnimationFrame(), x, y, null)
        zombie.drawHealthBar(canvas)
    }

    override fun getAnimationFrame(): Bitmap {
        if(Clock.millisecondsHavePassedSince(lastFrameUpdateTime, 100)){
            lastFrameUpdateTime = System.nanoTime()
            frameNum++
            if(frameNum > animation.size - 1) frameNum = animation.size - 1
        }
        return animation[frameNum]
    }

    override fun onSuccessfulSwipe() { }

    override fun update() {
        if(Clock.millisecondsHavePassedSince(startTime, 3000)){
            sensorManager.unregisterListener(shakeSensor)
            zombie.state = AttackZombie(zombie)
        }
    }

    private fun setupShakeSensor() {
        sensorManager = zombie.context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer  = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        shakeSensor.setOnShakeListener(object : ShakeSensor.OnShakeListener {
            override fun onShake(count: Int) {
                sensorManager.unregisterListener(shakeSensor)
                zombie.state = AliveZombie(zombie)
                ZombieAttackEvent.complete = true
                FirstDefenceEvent.trigger()
            }
        })
    }
}
