package viruswiper.game.rotation

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager

class RotationReceiver(private val context: Context, private val rotationReceiver: RotationHandler) {
    private lateinit var sensorManager: SensorManager
    private lateinit var accelerometer: Sensor
    private var rotationSensor: RotationSensor = RotationSensor()

    init {
        setUpRotationSensor()
    }

    private fun setUpRotationSensor() {
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer  = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)
        rotationSensor.setOnRotationListener(object : RotationSensor.OnRotationListener {
            override fun onRotate(pitch: Double, tilt: Double, azimuth: Double) {
                rotationReceiver.onRotate(pitch, tilt, azimuth)
            }
        })
        registerListener()
    }

    private fun registerListener(){
        sensorManager.registerListener(rotationSensor, accelerometer, SensorManager.SENSOR_DELAY_UI)
    }

    fun onPause(){
        sensorManager.unregisterListener(rotationSensor)
    }

    fun onResume(){
        registerListener()
    }
}