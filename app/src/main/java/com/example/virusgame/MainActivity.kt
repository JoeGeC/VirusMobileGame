package com.example.virusgame

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
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
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
    }

    override fun onPause() {
        super.onPause()
        SoundManager.pause()
    }

    override fun onResume() {
        super.onResume()
        SoundManager.resume()
    }

    override fun onBackPressed() {}
}