package com.example.virusgame.game.events

import com.example.virusgame.R

object FirstDamageTaken: Event() {
    override val name = context.getString(R.string.first_damage_taken_event_name)

    override fun trigger() {
        if(!complete && !ZombieAttackEvent.complete){
            speech.setSpeechText(context.getString(R.string.first_damage_advice))
            complete = true
        }
    }
}
