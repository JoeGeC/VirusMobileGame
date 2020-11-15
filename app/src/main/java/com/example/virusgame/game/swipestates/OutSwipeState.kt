package com.example.virusgame.game.swipestates

import com.example.virusgame.game.SwipeTaker
import com.example.virusgame.game.vector2.IntVector2

class OutSwipeState : SwipeState {
    override fun onTouch(touchPos: IntVector2, swipeTaker: SwipeTaker): SwipeState {
        if(touchPos.isInside(swipeTaker.hitRect))
            return InsideSwipeState()
        return this
    }
}
