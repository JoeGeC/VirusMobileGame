package viruswiper.game.events

import viruswiper.MainActivity
import viruswiper.speech.SpeechSetter

abstract class Event {
    abstract val name: String
    lateinit var speech: SpeechSetter
    val context = MainActivity.applicationContext()
    private var complete = false
    abstract fun trigger()

    open fun completeEvent(){
        complete = true
    }

    fun isComplete(): Boolean = complete

    fun setComplete(value: Boolean) { complete = value }
}