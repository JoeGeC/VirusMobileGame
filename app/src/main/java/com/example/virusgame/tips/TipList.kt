package com.example.virusgame.tips

import android.content.Context
import com.example.virusgame.R

object TipList {
    fun getList(context: Context): List<String> {
        return listOf(
            context.getString(R.string.rotate_device_tip),
            context.getString(R.string.shake_tip),
            context.getString(R.string.shake_tip_2),
            context.getString(R.string.gold_tip),
            context.getString(R.string.abilities_tip),
            context.getString(R.string.abilities_tip_2),
            context.getString(R.string.buying_abilities_tip),
            context.getString(R.string.chest_tip),
            context.getString(R.string.potion_tip),
            context.getString(R.string.death_tip),
            context.getString(R.string.health_recovery_zombie_tip),
            context.getString(R.string.invulnerable_zombie_tip)
        )
    }
}