package com.example.virusgame.game.events

import com.example.virusgame.R

object FirstTimePlayingEvent : Event() {
    override fun trigger(){
        if(!complete)
            speech.setSpeechText(context.getString(R.string.intro))
    }
}