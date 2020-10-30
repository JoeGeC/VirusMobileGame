package com.example.virusgame.game.events

import com.example.virusgame.R

object FirstDefenceEvent : Event() {
    override fun trigger() {
        if(!complete) {
            speech.setSpeechText(context.getString(R.string.first_defence))
            complete = true
        }
    }
}