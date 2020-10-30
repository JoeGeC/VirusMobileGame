package com.example.virusgame.game.events

import com.example.virusgame.R

object FirstDamageTaken: Event() {
    override fun trigger() {
        if(!complete){
            speech.setSpeechText(context.getString(R.string.first_damage_advice))
            complete = true
        }
    }

}
