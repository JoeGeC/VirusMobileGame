package viruswiper.shop

import android.content.Context
import viruswiper.R
import viruswiper.SaveManager
import viruswiper.shop.items.ShopItem
import viruswiper.shop.items.ShopItemSaveData
import viruswiper.shop.items.abilities.FireAbilityItem
import viruswiper.shop.items.abilities.IceAbilityItem
import viruswiper.shop.items.abilities.LightningAbilityItem
import viruswiper.shop.items.healthPotions.HealthPotion
import viruswiper.shop.items.healthPotions.HugeHealthPotion
import viruswiper.shop.items.healthPotions.LargeHealthPotion
import viruswiper.shop.items.healthPotions.SmallHealthPotion

object ShopList {
    fun getItems(context: Context): List<ShopItem> {
        val items = SaveManager.loadShop()
        if(items != null && items.isNotEmpty()) return factory(items, context)
        return newShop(context)
    }

    fun newShop(context: Context): List<ShopItem>{
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

    private fun createItem(item: ShopItem, saveData: ShopItemSaveData) : ShopItem {
        item.saveData = saveData
        return item
    }
}