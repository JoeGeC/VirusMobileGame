package viruswiper.game.rotation

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import kotlin.math.*


class RotationSensor : SensorEventListener {
    private var rotationListener: OnRotationListener? = null
    private var x: Double = 0.0
    private var y: Double = 0.0
    private var z: Double = 0.0
    private var w: Double = 0.0

    fun setOnRotationListener(listener: OnRotationListener?) {
        rotationListener = listener
    }

    interface OnRotationListener {
        fun onRotate(pitch: Double, tilt: Double, azimuth: Double)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) { }

    override fun onSensorChanged(event: SensorEvent) {
        val vector = convertFloatsToDoubles(normaliseVector(event))
        setLetterRepresentatives(vector)
        val pitch = calculatePitch()
        val tilt = calculateTilt()
        val azimuth = calculateAzimuth()
        rotationListener?.onRotate(pitch, tilt, azimuth)
    }

    private fun normaliseVector(event: SensorEvent): FloatArray {
        val vector: FloatArray = event.values.clone()
        val normal: Float = sqrt(vector[0] * vector[0] + vector[1] * vector[1] + vector[2] * vector[2])
        vector[0] /= normal
        vector[1] /= normal
        vector[2] /= normal
        return vector
    }

    private fun convertFloatsToDoubles(floatArray: FloatArray): DoubleArray {
        val result = DoubleArray(floatArray.size)
        for (i in floatArray.indices) result[i] = floatArray[i].toDouble()
        return result
    }

    private fun setLetterRepresentatives(normalisedVector: DoubleArray) {
        x = normalisedVector[0]
        y = normalisedVector[1]
        z = normalisedVector[2]
        w = normalisedVector[3]
    }

    private fun calculatePitch(): Double {
        val sinP = 2.0 * (w * x + y * z)
        val cosP = 1.0 - 2.0 * (x * x + y * y)
        return atan2(sinP, cosP) * (180 / Math.PI)
    }

    private fun calculateTilt(): Double {
        val sinT = 2.0 * (w * y - z * x)
        return if (abs(sinT) >= 1) {
            (Math.PI / 2).withSign(sinT) * (180 / Math.PI)
        } else
            asin(sinT) * (180 / Math.PI)
    }

    private fun calculateAzimuth(): Double {
        val sinA = 2.0 * (w * z + x * y)
        val cosA = 1.0 - 2.0 * (y * y + z * z)
        return atan2(sinA, cosA) * (180 / Math.PI)
    }
}