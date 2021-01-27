package viruswiper.game.events

import viruswiper.R

object FirstBossEvent: Event() {
    override val name: String = context.getString(R.string.firstBossEvent)

    override fun trigger() {
        if(!isComplete())
            speech.setTypedPauseMessage(context.getString(R.string.firstBossEventMessage))
    }

    override fun completeEvent() {
        if(!isComplete()){
            super.completeEvent()
            speech.setTypedPauseMessage(context.getString(R.string.firstHeartMessage))
        }
    }
}