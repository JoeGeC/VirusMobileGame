package com.example.virusgame.game.events

import com.example.virusgame.R

object ZombieAttackEvent : Event(){
    override val name = context.getString(R.string.zombie_attack_event_name)

    override fun trigger(){
        if(!isComplete())
            speech.setMessage(context.getString(R.string.shake_advice))
    }

    override fun completeEvent() {
        if(!isComplete()){
            super.completeEvent()
            speech.setMessage(context.getString(R.string.first_defence))
        }
    }

    fun onFail(){
        if(!isComplete())
            speech.setMessage(context.getString(R.string.first_damage_advice))
    }
}
