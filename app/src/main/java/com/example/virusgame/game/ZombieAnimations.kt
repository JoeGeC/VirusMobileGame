package com.example.virusgame.game

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.virusgame.R

class ZombieAnimations(private var context: Context) {
    fun idleAnimation1(): List<Bitmap>{
        return listOf(
            getResource(R.drawable.zombie_idle_001),
            getResource(R.drawable.zombie_idle_002),
            getResource(R.drawable.zombie_idle_003),
            getResource(R.drawable.zombie_idle_004),
            getResource(R.drawable.zombie_idle_005),
            getResource(R.drawable.zombie_idle_006),
            getResource(R.drawable.zombie_idle_007),
            getResource(R.drawable.zombie_idle_008),
            getResource(R.drawable.zombie_idle_009),
            getResource(R.drawable.zombie_idle_010),
            getResource(R.drawable.zombie_idle_011)
        )
    }

    private fun getResource(resource: Int): Bitmap{
        return BitmapFactory.decodeResource(context.resources, resource)
    }
}