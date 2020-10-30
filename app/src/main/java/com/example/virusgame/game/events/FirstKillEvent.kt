package com.example.virusgame.game.events

import com.example.virusgame.R

object FirstKillEvent : Event() {
    override val name = context.getString(R.string.first_kill_event_name)

    override fun trigger(){
        if(!complete){
            speech.setSpeechText(context.getString(R.string.first_kill))
            complete = true
        }
    }
}