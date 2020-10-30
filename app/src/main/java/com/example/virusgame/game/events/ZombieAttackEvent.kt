package com.example.virusgame.game.events

import com.example.virusgame.R

object ZombieAttackEvent : Event(){
    override val name = context.getString(R.string.zombie_attack_event_name)

    override fun trigger(){
        if(!complete)
            speech.setSpeechText(context.getString(R.string.shake_advice))
    }
}
