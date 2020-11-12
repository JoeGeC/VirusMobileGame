package com.example.virusgame.shop.items.abilities

import android.content.Context
import com.example.virusgame.R

class FireAbilityItem(override val context: Context) : AbilityItem() {
    override val imageResource: Int = R.drawable.fireball_ability_icon
    override val itemName: String = context.getString(R.string.fireAbility)
    override val description: String = "Deals 4x damage. Cooldown: 10 secs."
    override val price: Int = 200
}