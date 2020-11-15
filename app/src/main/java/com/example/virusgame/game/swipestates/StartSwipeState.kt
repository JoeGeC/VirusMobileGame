package com.example.virusgame.game.swipestates

import com.example.virusgame.game.SwipeTaker
import com.example.virusgame.game.vector2.IntVector2

class StartSwipeState : SwipeState {
    override fun onTouch(touchPos: IntVector2, swipeTaker: SwipeTaker) : SwipeState {
        if(touchPos.isOutside(swipeTaker.hitRect))
            return OutSwipeState()
        return this
    }
}