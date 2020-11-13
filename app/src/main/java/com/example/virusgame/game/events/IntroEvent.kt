package com.example.virusgame.game.events

import com.example.virusgame.R

object IntroEvent : Event() {
    override val name = context.getString(R.string.first_time_playing_event_name)

    override fun trigger(){
        if(!isComplete())
            speech.setSpeechText(context.getString(R.string.intro))
        else
            speech.setSpeechText(context.getString(R.string.welcome_back))
    }

    override fun onComplete() {
        if(!isComplete()){
            super.onComplete()
            speech.setSpeechText(context.getString(R.string.first_kill))
        }
    }
}