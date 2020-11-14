package com.example.virusgame.game.events

import com.example.virusgame.R

object FirstAbilityEvent: Event(){
    override val name: String = context.getString(R.string.firstAbilityEventName)

    override fun trigger() {
        if(!isComplete()){
            speech.setSpeechText(context.getString(R.string.firstAbilityMessage))
        }
    }

    override fun onComplete() {
        if(!isComplete()){
            super.onComplete()
            speech.setSpeechText(context.getString(R.string.firstAbilityUsedMessage))
        }
    }
}