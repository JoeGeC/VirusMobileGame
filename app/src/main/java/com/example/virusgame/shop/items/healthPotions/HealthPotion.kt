package com.example.virusgame.shop.items.healthPotions

import android.content.Context
import com.example.virusgame.R

class HealthPotion(override val context: Context): HealthItem() {
    override val imageResource: Int = R.drawable.green_potion_icon
    override val effect: Int = 50
    override val description: String = context.getString(R.string.recovers) + " $effect " + context.getString(R.string.health).toLowerCase()
    override val price: Int = 400

    init {
        saveData.itemName = context.getString(R.string.healthPotion)
    }
}