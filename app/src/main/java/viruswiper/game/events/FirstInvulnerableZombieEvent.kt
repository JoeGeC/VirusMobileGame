package viruswiper.game.events

import viruswiper.R

object FirstInvulnerableZombieEvent : Event() {
    override val name: String = context.getString(R.string.firstInvulnerableZombieEvent)

    override fun trigger() {
        if(!isComplete())
            speech.setTypedPauseMessage(context.getString(R.string.firstInvulnerableZombieMessage))
    }
}
