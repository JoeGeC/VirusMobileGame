package com.example.virusgame.game.zombie

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.virusgame.R

class ZombieAnimations(private var context: Context) {
    private fun getResource(resource: Int): Bitmap{
        return BitmapFactory.decodeResource(context.resources, resource)
    }

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

    fun dieAnimation1(): List<Bitmap>{
        return listOf(
            getResource(R.drawable.zombie_die_000),
            getResource(R.drawable.zombie_die_001),
            getResource(R.drawable.zombie_die_002),
            getResource(R.drawable.zombie_die_003),
            getResource(R.drawable.zombie_die_004),
            getResource(R.drawable.zombie_die_005),
            getResource(R.drawable.zombie_die_006),
            getResource(R.drawable.zombie_die_007)
        )
    }

    fun preAttackAnimation1(): List<Bitmap>{
        return listOf(
            getResource(R.drawable.zombie_attack_000),
            getResource(R.drawable.zombie_attack_001),
            getResource(R.drawable.zombie_attack_002),
            getResource(R.drawable.zombie_attack_003)
        )
    }

    fun attackAnimation1(): List<Bitmap> {
        return listOf(
            getResource(R.drawable.zombie_attack_004),
            getResource(R.drawable.zombie_attack_005),
            getResource(R.drawable.zombie_attack_006),
            getResource(R.drawable.zombie_attack_007),
            getResource(R.drawable.zombie_attack_008),
            getResource(R.drawable.zombie_attack_009),
            getResource(R.drawable.zombie_attack_010),
            getResource(R.drawable.zombie_attack_011)
        )
    }

    fun hurtAnimation1(): List<Bitmap> {
        return listOf(
            getResource(R.drawable.zombie_hurt_000),
            getResource(R.drawable.zombie_hurt_001),
            getResource(R.drawable.zombie_hurt_002),
            getResource(R.drawable.zombie_hurt_003),
            getResource(R.drawable.zombie_hurt_004),
            getResource(R.drawable.zombie_hurt_005),
            getResource(R.drawable.zombie_hurt_006),
            getResource(R.drawable.zombie_hurt_007)
        )
    }
}