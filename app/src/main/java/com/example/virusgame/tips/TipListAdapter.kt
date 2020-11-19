package com.example.virusgame.tips

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.virusgame.R

class TipListAdapter(private val tips: List<String>) : RecyclerView.Adapter<TipListAdapter.TipItemViewHolder>(){
    class TipItemViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tip_item, parent, false) as ConstraintLayout
        return TipItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: TipItemViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.message).text = tips[position]
        holder.itemView.visibility = View.VISIBLE
    }

    override fun getItemCount(): Int = tips.size
}
