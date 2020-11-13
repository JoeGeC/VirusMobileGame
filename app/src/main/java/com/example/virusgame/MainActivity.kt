package com.example.virusgame

import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(){
    init {
        instance = this
    }

    companion object {
        private var instance: MainActivity? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFullScreen()
        setContentView(R.layout.activity_main)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragmentContainer, MainMenu())
        fragmentTransaction.commit()

        SaveManager.loadSettings()
        SoundManager.playMusic(applicationContext, R.raw.music_wind_and_tree)
    }

    private fun setFullScreen() {
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    override fun onPause() {
        super.onPause()
        SoundManager.pause()
    }

    override fun onResume() {
        super.onResume()
        SoundManager.resume()
    }
}