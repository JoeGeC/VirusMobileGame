package com.example.virusgame.game.events

import com.example.virusgame.R

object FirstHealthRecoveryZombieEvent : Event() {
    override val name: String = context.getString(R.string.firstHealthRecoveryZombieEvent)

    override fun trigger() {
        if(!isComplete())
            speech.setMessage(context.getString(R.string.firstHealthRecoveryZombieMessage))
    }
}
