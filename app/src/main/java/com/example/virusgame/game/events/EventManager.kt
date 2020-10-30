package com.example.virusgame.game.events

import com.example.virusgame.game.Speech

object EventManager {
    private var events = listOf(
        FirstDamageTaken,
        FirstDefenceEvent,
        FirstKillEvent,
        FirstTimePlayingEvent,
        ZombieAttackEvent
    )

    fun setupEvents(speech: Speech){
        events.forEach{ event ->
            event.speech = speech
        }
    }
}