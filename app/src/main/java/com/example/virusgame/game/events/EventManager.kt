package com.example.virusgame.game.events

import com.example.virusgame.MainActivity
import com.example.virusgame.game.Speech

class EventManager {
    var context = MainActivity.applicationContext()

    private var events = listOf(
        FirstDamageTaken,
        FirstDefenceEvent,
        FirstKillEvent,
        IntroEvent,
        ZombieAttackEvent
    )

    fun setupEvents(speech: Speech){
        events.forEach{ event ->
            event.speech = speech
        }
    }

    fun saveEvents(): Map<String, Boolean>{
        return events.associateBy({it.name}, {it.complete})
    }

    fun loadEvents(eventsStatus: Map<String, Boolean>){
        events.forEach{ event ->
            eventsStatus.forEach{ status ->
                if(event.name == status.key){
                    event.complete = status.value
                }
            }
        }
    }
}