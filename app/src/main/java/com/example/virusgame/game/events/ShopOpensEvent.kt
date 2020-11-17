package com.example.virusgame.game.events

import com.example.virusgame.R

object ShopOpensEvent: Event() {
    override val name: String = context.getString(R.string.shopOpensEventName)

    override fun trigger() {
        if(!isComplete())
            speech.setMessage(context.getString(R.string.shopOpensMessage))
    }
}