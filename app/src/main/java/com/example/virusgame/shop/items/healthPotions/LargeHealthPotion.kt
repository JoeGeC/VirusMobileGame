package com.example.virusgame.shop.items.healthPotions

import android.content.Context
import com.example.virusgame.R

class LargeHealthPotion(override val context: Context): HealthItem() {
    override val imageResource: Int = R.drawable.blue_potion_icon
    override val effect: Int = 100
    override val description: String = "Recovers $effect health"
    override val price: Int = 700

    init {
        saveData.itemName = context.getString(R.string.largeHealthPotion)
    }
}