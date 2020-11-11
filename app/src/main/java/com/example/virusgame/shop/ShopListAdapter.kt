package com.example.virusgame.shop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.virusgame.R
import com.example.virusgame.shop.items.ShopItem

class ShopListAdapter (private val items: Array<ShopItem>) : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>(){
    class ShopItemViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shop_item, parent, false) as ConstraintLayout
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        holder.itemView.findViewById<ImageView>(R.id.itemImage).setImageResource(items[position].imageResource)
        holder.itemView.findViewById<TextView>(R.id.itemName).text = items[position].itemName
        holder.itemView.findViewById<TextView>(R.id.itemDescription).text = items[position].description
        holder.itemView.findViewById<TextView>(R.id.price).text = items[position].price.toString()
    }

    override fun getItemCount() = items.size

}