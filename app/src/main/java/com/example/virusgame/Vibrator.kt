package com.example.virusgame

import android.content.Context
import android.os.VibrationEffect
import android.os.Vibrator

class Vibrator(context: Context) {
    private var vibrator: Vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

    fun vibrate(milliseconds: Long){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
            vibrator.vibrate(VibrationEffect.createOneShot(milliseconds, VibrationEffect.DEFAULT_AMPLITUDE))
        else
            vibrator.vibrate(milliseconds)
    }
}