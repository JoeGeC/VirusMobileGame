package com.example.virusgame.game.events

import com.example.virusgame.R

object FirstBossEvent: Event() {
    override val name: String = context.getString(R.string.firstBossEvent)

    override fun trigger() {
        if(!isComplete())
            speech.setMessage(context.getString(R.string.firstBossEventMessage))
    }

    override fun onComplete() {
        if(!isComplete()){
            super.onComplete()
            speech.setMessage(context.getString(R.string.firstHeartMessage))
        }
    }
}