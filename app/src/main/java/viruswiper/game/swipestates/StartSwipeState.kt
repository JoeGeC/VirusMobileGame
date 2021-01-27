package viruswiper.game.swipestates

import viruswiper.game.SwipeTaker
import viruswiper.game.vector2.IntVector2

class StartSwipeState : SwipeState {
    override fun onTouch(touchPos: IntVector2, swipeTaker: SwipeTaker) : SwipeState {
        if(touchPos.isOutside(swipeTaker.hitRect))
            return OutSwipeState()
        return this
    }
}