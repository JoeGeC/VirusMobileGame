package com.example.virusgame

import android.content.Context
import android.os.VibrationEffect
import android.os.Vibrator
import kotlin.concurrent.thread

class Vibrator(context: Context) {
    private var vibrator: Vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

    fun vibrate(milliseconds: Long){
        stop()
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
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
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
            vibrator.vibrate(VibrationEffect.createWaveform(pattern, repeat))
        else
            vibrator.vibrate(pattern, repeat)
    }

    fun stop(){
        vibrator.cancel()
    }
}