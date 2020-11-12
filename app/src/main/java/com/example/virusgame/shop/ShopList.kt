package com.example.virusgame.shop

import android.content.Context
import com.example.virusgame.shop.items.ShopItem
import com.example.virusgame.shop.items.abilities.FireAbilityItem
import com.example.virusgame.shop.items.abilities.IceAbilityItem
import com.example.virusgame.shop.items.abilities.LightningAbilityItem
import com.example.virusgame.shop.items.healthPotions.*

object ShopList {
    fun getItems(context: Context): Array<ShopItem> {
        return arrayOf(
            FireAbilityItem(context),
            LightningAbilityItem(context),
            IceAbilityItem(context),
            SmallHealthPotion(context),
            HealthPotion(context),
            LargeHealthPotion(context),
            HugeHealthPotion(context)
        )
    }
}