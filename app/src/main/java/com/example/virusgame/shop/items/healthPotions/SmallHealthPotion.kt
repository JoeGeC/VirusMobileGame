package com.example.virusgame.shop.items.healthPotions

import android.content.Context
import com.example.virusgame.R

class SmallHealthPotion(override val context: Context): HealthItem() {
    override val imageResource: Int = R.drawable.red_potion_icon
    override val effect: Int = 10
    override val description: String = context.getString(R.string.recovers) + " $effect " + context.getString(R.string.health).toLowerCase()
    override val price: Int = 100

    init {
        saveData.itemName = context.getString(R.string.smallHealthPotion)
    }
}