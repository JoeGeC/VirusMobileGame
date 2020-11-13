package com.example.virusgame.game.ui

import android.animation.ObjectAnimator
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.virusgame.R

class WaveUiManager(view: View) : WaveUiHandler {
    var icon1: ConstraintLayout = view.findViewById(R.id.icon1)
    var icon2: ConstraintLayout = view.findViewById(R.id.icon2)
    var icon3: ConstraintLayout = view.findViewById(R.id.icon3)
    var icon4: ConstraintLayout = view.findViewById(R.id.icon4)
    var icon5: ConstraintLayout = view.findViewById(R.id.icon5)

    init {
        nextWave()
    }

    override fun nextWave() {
        ObjectAnimator.ofFloat(icon5, "translationX", 100f).apply {
            duration = 2000
            start()
        }

//        icon1.x
    }
}
