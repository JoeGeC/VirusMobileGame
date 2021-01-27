package viruswiper.game.shake

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

class ShakeSensor : SensorEventListener {
    private val shakeThresholdGravity = 1.4f
    private var shakeListener: ShakeListener? = null

    fun setOnShakeListener(listener: ShakeListener?) {
        shakeListener = listener
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onSensorChanged(event: SensorEvent) {
        if (shakeListener == null) return
        val x = event.values[0]
        val y = event.values[1]
        val z = event.values[2]
        val gX = x / SensorManager.GRAVITY_EARTH
        val gY = y / SensorManager.GRAVITY_EARTH
        val gZ = z / SensorManager.GRAVITY_EARTH

        // gForce will be close to 1 when there is no movement.
        val gForce: Float = kotlin.math.sqrt(gX * gX + gY * gY + gZ * gZ)
        if (gForce > shakeThresholdGravity) {
            shakeListener!!.onShake()
        }
    }
}