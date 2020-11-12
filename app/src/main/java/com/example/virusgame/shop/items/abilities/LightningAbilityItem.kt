package com.example.virusgame.shop.items.abilities

import android.content.Context
import com.example.virusgame.R

class LightningAbilityItem(override val context: Context) : AbilityItem()  {
    override val imageResource: Int = R.drawable.lightning_ability_icon
    override val description: String = "Deals 3x damage. Cooldown: 5 secs."
    override val price: Int = 500

    init {
        saveData.itemName = context.getString(R.string.lightningAbility)
    }
}