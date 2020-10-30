package com.example.virusgame.game.events

import com.example.virusgame.R

object FirstDefenceEvent : Event() {
    override val name = context.getString(R.string.first_defence_event_name)

    override fun trigger() {
        if(!complete) {
            speech.setSpeechText(context.getString(R.string.first_defence))
            complete = true
        }
    }
}