package com.example.virusgame.game.events

import com.example.virusgame.R

object FirstInvulnerableZombieEvent : Event() {
    override val name: String = context.getString(R.string.firstInvulnerableZombieEvent)

    override fun trigger() {
        if(!isComplete())
            speech.setTypedMessage(context.getString(R.string.firstInvulnerableZombieMessage))
    }
}
