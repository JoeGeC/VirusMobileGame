package com.example.virusgame

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.virusgame.game.ShakeSensor
import com.example.virusgame.game.ShakeSensor.OnShakeListener


class MainActivity : AppCompatActivity(){
    private lateinit var sensorManager: SensorManager
    private lateinit var accelerometer: Sensor
    private var shakeSensor: ShakeSensor = ShakeSensor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFullScreen()
        setContentView(R.layout.activity_main)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragmentContainer, MainMenu())
        fragmentTransaction.commit()

        setupShakeSensor()
    }

    private fun setFullScreen() {
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    private fun setupShakeSensor() {
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer  = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        shakeSensor.setOnShakeListener(object : OnShakeListener {
            override fun onShake(count: Int) {
                Toast.makeText(applicationContext, "Shake!", Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(
            shakeSensor,
            accelerometer,
            SensorManager.SENSOR_DELAY_UI
        )
    }

    override fun onPause() {
        sensorManager.unregisterListener(shakeSensor)
        super.onPause()
    }
}