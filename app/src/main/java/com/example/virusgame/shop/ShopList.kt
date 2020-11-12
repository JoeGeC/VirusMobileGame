package com.example.virusgame.shop

import android.content.Context
import com.example.virusgame.R
import com.example.virusgame.SaveManager
import com.example.virusgame.shop.items.ShopItem
import com.example.virusgame.shop.items.ShopItemSaveData
import com.example.virusgame.shop.items.abilities.FireAbilityItem
import com.example.virusgame.shop.items.abilities.IceAbilityItem
import com.example.virusgame.shop.items.abilities.LightningAbilityItem
import com.example.virusgame.shop.items.healthPotions.*

object ShopList {
    fun getItems(context: Context): List<ShopItem> {
        val items = SaveManager.loadShop()
        if(items != null) return factory(items, context)
        return listOf(
            FireAbilityItem(context),
            LightningAbilityItem(context),
            IceAbilityItem(context),
            SmallHealthPotion(context),
            HealthPotion(context),
            LargeHealthPotion(context),
            HugeHealthPotion(context)
        )
    }

    private fun factory(itemSaveData: List<ShopItemSaveData>, context: Context): List<ShopItem> {
        val result = mutableListOf<ShopItem>()
        for (saveData in itemSaveData){
            when (saveData.itemName) {
                context.getString(R.string.fireAbility) ->
                    result.add(createItem(FireAbilityItem(context), saveData))
                context.getString(R.string.lightningAbility) ->
                    result.add(createItem(LightningAbilityItem(context), saveData))
                context.getString(R.string.iceAbility) ->
                    result.add(createItem(IceAbilityItem(context), saveData))
                context.getString(R.string.smallHealthPotion) ->
                    result.add(createItem(SmallHealthPotion(context), saveData))
                context.getString(R.string.healthPotion) ->
                    result.add(createItem(HealthPotion(context), saveData))
                context.getString(R.string.largeHealthPotion) ->
                    result.add(createItem(LargeHealthPotion(context), saveData))
                context.getString(R.string.hugeHealthPotion) ->
                    result.add(createItem(HugeHealthPotion(context), saveData))
            }
        }
        return result
    }

    private fun createItem(item: ShopItem, saveData: ShopItemSaveData) : ShopItem{
        item.saveData = saveData
        return item
    }
}