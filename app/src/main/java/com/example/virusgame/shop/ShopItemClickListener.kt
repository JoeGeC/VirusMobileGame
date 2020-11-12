package com.example.virusgame.shop

import android.view.View
import com.example.virusgame.shop.items.ShopItem

class ShopItemClickListener(private val shopItem: ShopItem, private val adapter: ShopListAdapter) : View.OnClickListener {
    override fun onClick(view: View?) {
        shopItem.onClick(adapter)
        adapter.notifyDataSetChanged()
    }
}
