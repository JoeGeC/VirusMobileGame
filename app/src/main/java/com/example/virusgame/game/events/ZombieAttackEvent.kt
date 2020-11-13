package com.example.virusgame.game.events

import com.example.virusgame.R

object ZombieAttackEvent : Event(){
    override val name = context.getString(R.string.zombie_attack_event_name)

    override fun trigger(){
        if(!isComplete())
            speech.setQuickSpeechText(context.getString(R.string.shake_advice))
    }

    override fun onComplete() {
        if(!isComplete()){
            super.onComplete()
            speech.setSpeechText(context.getString(R.string.first_defence))
        }
    }

    fun onFail(){
        if(!isComplete())
            speech.setSpeechText(context.getString(R.string.first_damage_advice))
    }
}
