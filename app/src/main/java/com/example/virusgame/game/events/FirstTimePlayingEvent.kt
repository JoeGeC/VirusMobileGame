package com.example.virusgame.game.events

import com.example.virusgame.MainActivity
import com.example.virusgame.R
import com.example.virusgame.game.Speech

object FirstTimePlayingEvent : Event() {
    override fun trigger(){
        if(!complete)
            Speech.setSpeechText(MainActivity.applicationContext().getString(R.string.intro))
    }
}