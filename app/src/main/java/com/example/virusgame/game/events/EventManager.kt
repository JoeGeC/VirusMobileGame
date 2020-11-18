package com.example.virusgame.game.events

import com.example.virusgame.MainActivity
import com.example.virusgame.SpeechSetter
import com.example.virusgame.game.Speech

class EventManager {
    var context = MainActivity.applicationContext()

    private var events = listOf(
        IntroEvent,
        ZombieAttackEvent,
        FirstBossEvent,
        ShopOpensEvent,
        FirstAbilityEvent,
        FirstHealthRecoveryZombieEvent,
        FirstInvulnerableZombieEvent
    )

    fun setupEvents(speech: SpeechSetter){
        events.forEach{ event ->
            event.speech = speech
        }
    }

    fun saveEvents(): Map<String, Boolean>{
        return events.associateBy({it.name}, {it.isComplete()})
    }

    fun loadEvents(eventsStatus: Map<String, Boolean>){
        events.forEach{ event ->
            eventsStatus.forEach{ status ->
                if(event.name == status.key){
                    event.setComplete(status.value)
                }
            }
        }
    }

    fun clearEvents(){
        events.forEach{ event ->
            event.setComplete(false)
        }
    }
}