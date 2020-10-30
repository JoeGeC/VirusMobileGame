package com.example.virusgame.game.events

import com.example.virusgame.R

object FirstTimePlayingEvent : Event() {
    override val name = context.getString(R.string.first_time_playing_event_name)

    override fun trigger(){
        if(!complete)
            speech.setSpeechText(context.getString(R.string.intro))
        else
            speech.setSpeechText(context.getString(R.string.welcome_back))
    }
}