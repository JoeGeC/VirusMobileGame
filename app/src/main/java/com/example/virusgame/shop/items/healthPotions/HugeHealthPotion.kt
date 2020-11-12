package com.example.virusgame.shop.items.healthPotions

import android.content.Context
import com.example.virusgame.R

class HugeHealthPotion(override val context: Context): HealthItem() {
    override val imageResource: Int = R.drawable.yellow_potion_icon
    override val itemName: String = "Huge Health Potion"
    override val effect: Int = 200
    override val description: String = "Recovers $effect health"
    override val price: Int = 1200
}