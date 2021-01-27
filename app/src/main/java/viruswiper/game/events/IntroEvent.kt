package viruswiper.game.events

import viruswiper.R

object IntroEvent : Event() {
    override val name = context.getString(R.string.first_time_playing_event_name)

    override fun trigger(){
        if(!isComplete())
            speech.setTypedPauseMessage(context.getString(R.string.intro))
        else
            speech.setTypedMessage(context.getString(R.string.welcome_back))
    }

    override fun completeEvent() {
        if(!isComplete()){
            super.completeEvent()
            speech.setTypedMessage(context.getString(R.string.first_kill))
        }
    }
}