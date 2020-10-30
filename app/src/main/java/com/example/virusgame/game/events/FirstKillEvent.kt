package com.example.virusgame.game.events

import com.example.virusgame.R

object FirstKillEvent : Event() {
    override fun trigger(){
        if(!complete){
            speech.setSpeechText(context.getString(R.string.first_kill))
            complete = true
        }
    }
}