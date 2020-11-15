package com.example.virusgame

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.example.virusgame.game.uiHandlers.ShopHandler
import com.example.virusgame.shop.ShopFragment

class ShopIcon(context: Context, attributeSet: AttributeSet) : AppCompatImageView(context, attributeSet) {
    private var available = false
    private val availableImage = R.drawable.shop
    private val unavailableImage = R.drawable.shop_unavailable

    fun onClick(menuFragmentManager: MenuFragmentManager, shopHandler: ShopHandler){
        if(available) menuFragmentManager.openFragment(ShopFragment(shopHandler))
    }

    fun setAvailable(activity: Activity){
        available = true
        setImageResourceOnUiThread(activity, availableImage)
    }

    fun setUnavailable(activity: Activity){
        available = false
        setImageResourceOnUiThread(activity, unavailableImage)
    }

    private fun setImageResourceOnUiThread(activity: Activity, imageResource: Int) {
        activity.runOnUiThread { setImageResource(imageResource) }
    }

}