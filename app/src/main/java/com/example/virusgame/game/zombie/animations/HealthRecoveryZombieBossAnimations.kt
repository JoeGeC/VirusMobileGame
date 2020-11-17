package com.example.virusgame.game.zombie.animations

import android.content.Context
import android.graphics.Bitmap
import com.example.virusgame.R

class HealthRecoveryZombieBossAnimations(context: Context) : ZombieAnimations(context) {
    override fun idleAnimation(): List<Bitmap>{
        return listOf(
            getResource(R.drawable.boss_blonde_zombie_idle_000),
            getResource(R.drawable.boss_blonde_zombie_idle_001),
            getResource(R.drawable.boss_blonde_zombie_idle_002),
            getResource(R.drawable.boss_blonde_zombie_idle_003),
            getResource(R.drawable.boss_blonde_zombie_idle_004),
            getResource(R.drawable.boss_blonde_zombie_idle_005),
            getResource(R.drawable.boss_blonde_zombie_idle_006),
            getResource(R.drawable.boss_blonde_zombie_idle_007),
            getResource(R.drawable.boss_blonde_zombie_idle_008),
            getResource(R.drawable.boss_blonde_zombie_idle_009),
            getResource(R.drawable.boss_blonde_zombie_idle_010),
            getResource(R.drawable.boss_blonde_zombie_idle_011)
        )
    }

    override fun dieAnimation(): List<Bitmap>{
        return listOf(
            getResource(R.drawable.boss_blonde_zombie_die_000),
            getResource(R.drawable.boss_blonde_zombie_die_001),
            getResource(R.drawable.boss_blonde_zombie_die_002),
            getResource(R.drawable.boss_blonde_zombie_die_003),
            getResource(R.drawable.boss_blonde_zombie_die_004),
            getResource(R.drawable.boss_blonde_zombie_die_005),
            getResource(R.drawable.boss_blonde_zombie_die_006),
            getResource(R.drawable.boss_blonde_zombie_die_007)
        )
    }

    override fun preAttackAnimation(): List<Bitmap>{
        return listOf(
            getResource(R.drawable.boss_blonde_zombie_attack_000),
            getResource(R.drawable.boss_blonde_zombie_attack_001),
            getResource(R.drawable.boss_blonde_zombie_attack_002),
            getResource(R.drawable.boss_blonde_zombie_attack_003)
        )
    }

    override fun attackAnimation(): List<Bitmap> {
        return listOf(
            getResource(R.drawable.boss_blonde_zombie_attack_004),
            getResource(R.drawable.boss_blonde_zombie_attack_005),
            getResource(R.drawable.boss_blonde_zombie_attack_006),
            getResource(R.drawable.boss_blonde_zombie_attack_007),
            getResource(R.drawable.boss_blonde_zombie_attack_008),
            getResource(R.drawable.boss_blonde_zombie_attack_009),
            getResource(R.drawable.boss_blonde_zombie_attack_010),
            getResource(R.drawable.boss_blonde_zombie_attack_011)
        )
    }

    override fun hurtAnimation(): List<Bitmap> {
        return listOf(
            getResource(R.drawable.boss_blonde_zombie_hurt_000),
            getResource(R.drawable.boss_blonde_zombie_hurt_001),
            getResource(R.drawable.boss_blonde_zombie_hurt_002),
            getResource(R.drawable.boss_blonde_zombie_hurt_003),
            getResource(R.drawable.boss_blonde_zombie_hurt_004),
            getResource(R.drawable.boss_blonde_zombie_hurt_005),
            getResource(R.drawable.boss_blonde_zombie_hurt_006),
            getResource(R.drawable.boss_blonde_zombie_hurt_007)
        )
    }
}