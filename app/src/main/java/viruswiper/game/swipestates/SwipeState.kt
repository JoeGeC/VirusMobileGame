package viruswiper.game.swipestates

import viruswiper.game.SwipeTaker
import viruswiper.game.vector2.IntVector2

interface SwipeState {
    fun onTouch(touchPos: IntVector2, swipeTaker: SwipeTaker) : SwipeState
}