package viruswiper.game.shake

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import viruswiper.game.zombie.ZombieDamageHandler

class ShakeReceiver(private var context: Context, var zombieDamageHandler: ZombieDamageHandler){
    private lateinit var sensorManager: SensorManager
    private lateinit var accelerometer: Sensor
    private var shakeSensor: ShakeSensor = ShakeSensor()

    init {
        setupShakeSensor()
    }

    private fun setupShakeSensor() {
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer  = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        shakeSensor.setOnShakeListener(object : ShakeListener {
            override fun onShake() {
                zombieDamageHandler.shakeZombie()
            }
        })
        registerListener()
    }

    fun onPause(){
        sensorManager.unregisterListener(shakeSensor)
    }

    fun onResume(){
        registerListener()
    }

    private fun registerListener(){
        sensorManager.registerListener(shakeSensor, accelerometer, SensorManager.SENSOR_DELAY_UI)
    }
}
