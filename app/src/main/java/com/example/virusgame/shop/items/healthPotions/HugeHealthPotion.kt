package com.example.virusgame.shop.items.healthPotions

import android.content.Context
import com.example.virusgame.R

class HugeHealthPotion(override val context: Context): HealthItem() {
    override val imageResource: Int = R.drawable.yellow_potion_icon
    override val effect: Int = 200
    override val description: String = context.getString(R.string.recovers) + " $effect " + context.getString(R.string.health_recover)
    override val price: Int = 1200

    init {
        saveData.itemName = context.getString(R.string.hugeHealthPotion)
    }
}