package viruswiper.game.events

import viruswiper.R

object FirstHealthRecoveryZombieEvent : Event() {
    override val name: String = context.getString(R.string.firstHealthRecoveryZombieEvent)

    override fun trigger() {
        if(!isComplete())
            speech.setTypedPauseMessage(context.getString(R.string.firstHealthRecoveryZombieMessage))
    }
}
