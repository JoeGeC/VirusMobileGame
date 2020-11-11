package com.example.virusgame.shop

import android.view.View
import android.widget.ImageView
import com.example.virusgame.R

class ShopItemClickListener : View.OnClickListener {
    override fun onClick(view: View?) {
        view!!.findViewById<ImageView>(R.id.bought).visibility = View.VISIBLE
    }

}
