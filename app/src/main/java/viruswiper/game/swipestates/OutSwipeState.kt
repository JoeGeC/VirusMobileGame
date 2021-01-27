package viruswiper.game.swipestates

import viruswiper.game.SwipeTaker
import viruswiper.game.vector2.IntVector2

class OutSwipeState : SwipeState {
    override fun onTouch(touchPos: IntVector2, swipeTaker: SwipeTaker): SwipeState {
        if(touchPos.isInside(swipeTaker.hitRect))
            return InsideSwipeState()
        return this
    }
}
