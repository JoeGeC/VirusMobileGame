package com.example.virusgame.game.events

import com.example.virusgame.R

object FirstBossEvent: Event() {
    override val name: String = context.getString(R.string.firstBossEvent)

    override fun trigger() {
        if(!isComplete())
            speech.setTypedMessage(context.getString(R.string.firstBossEventMessage))
    }

    override fun completeEvent() {
        if(!isComplete()){
            super.completeEvent()
            speech.setTypedMessage(context.getString(R.string.firstHeartMessage))
        }
    }
}