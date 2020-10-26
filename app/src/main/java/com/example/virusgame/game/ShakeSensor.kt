package com.example.virusgame.game

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

class ShakeSensor : SensorEventListener {
    private val shakeThresholdGravity = 2.7f
    private var mListener: OnShakeListener? = null
    private var mShakeCount = 0

    fun setOnShakeListener(listener: OnShakeListener?) {
        mListener = listener
    }

    interface OnShakeListener {
        fun onShake(count: Int)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (mListener != null) {
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]
            val gX = x / SensorManager.GRAVITY_EARTH
            val gY = y / SensorManager.GRAVITY_EARTH
            val gZ = z / SensorManager.GRAVITY_EARTH

            // gForce will be close to 1 when there is no movement.
            val gForce: Float = kotlin.math.sqrt(gX * gX + gY * gY + gZ * gZ)
            if (gForce > shakeThresholdGravity) {
                mListener!!.onShake(mShakeCount)
            }
        }
    }
}