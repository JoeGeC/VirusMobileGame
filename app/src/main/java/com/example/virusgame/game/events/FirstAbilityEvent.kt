package com.example.virusgame.game.events

import com.example.virusgame.R

object FirstAbilityEvent: Event(){
    override val name: String = context.getString(R.string.firstAbilityEventName)

    override fun trigger() {
        if(!isComplete()){
            speech.setTypedPauseMessage(context.getString(R.string.firstAbilityMessage))
        }
    }

    override fun completeEvent() {
        if(!isComplete()){
            super.completeEvent()
            speech.setTypedPauseMessage(context.getString(R.string.firstAbilityUsedMessage))
        }
    }
}