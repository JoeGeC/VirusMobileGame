package com.example.virusgame.shop

import android.view.View
import com.example.virusgame.shop.items.ShopItem

class ShopItemClickListener(private val shopItem: ShopItem, private val adapter: ShopListAdapter) : View.OnClickListener {
    override fun onClick(view: View?) {
        if(shopItem.bought) equipItem()
        else if(adapter.shopHandler.purchase(shopItem)){
            shopItem.bought = true
            equipItem()
        }
        adapter.notifyDataSetChanged()
    }

    private fun equipItem() {
        adapter.unequipAll()
        adapter.shopHandler.equip(shopItem)
        shopItem.equipped = true
    }
}
