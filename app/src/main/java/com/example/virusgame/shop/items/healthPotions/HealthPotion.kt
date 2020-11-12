package com.example.virusgame.shop.items.healthPotions

import android.content.Context
import com.example.virusgame.R

class HealthPotion(override val context: Context): HealthItem() {
    override val imageResource: Int = R.drawable.green_potion_icon
    override val itemName: String = "Health Potion"
    override val effect: Int = 50
    override val description: String = "Recovers $effect health"
    override val price: Int = 400
}