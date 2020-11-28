package com.example.virusgame.vibrator

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import kotlin.concurrent.thread

class Vibrator(context: Context) {
    private var vibrator: Vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

    fun vibrate(milliseconds: Long){
        if(!VibrateManager.active) return
        stop()
        if (androidVersionIsOverO())
            vibrator.vibrate(VibrationEffect.createOneShot(milliseconds, VibrationEffect.DEFAULT_AMPLITUDE))
        else
            vibrator.vibrate(milliseconds)
        stopAfter(milliseconds)
    }

    private fun stopAfter(milliseconds: Long) {
        thread {
            Thread.sleep(milliseconds)
            stop()
        }
    }

    fun vibrate(pattern: LongArray, repeat: Int){
        if(!VibrateManager.active) return
        if (androidVersionIsOverO())
            vibrator.vibrate(VibrationEffect.createWaveform(pattern, repeat))
        else
            vibrator.vibrate(pattern, repeat)
    }

    //Using deprecated method for older versions of android
    private fun androidVersionIsOverO() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

    fun stop(){
        vibrator.cancel()
    }
}