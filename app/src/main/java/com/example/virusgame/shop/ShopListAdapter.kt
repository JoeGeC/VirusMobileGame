package com.example.virusgame.shop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.virusgame.R

class ShopListAdapter (private val items: Array<String>) : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>(){
    class ShopItemViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shop_item, parent, false) as ConstraintLayout
        // set the view's size, margins, paddings and layout parameters
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.itemName).text = items[position]
    }

    override fun getItemCount() = items.size

}