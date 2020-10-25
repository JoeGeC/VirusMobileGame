package com.example.virusgame.swipestates

import com.example.virusgame.SwipeTaker

class InsideSwipeState : SwipeState {
    override fun onTouch(xTouch: Int, yTouch: Int, swipeTaker: SwipeTaker): SwipeState {
        if(xTouch > swipeTaker.rect.right || xTouch < swipeTaker.rect.left || yTouch < swipeTaker.rect.top || yTouch > swipeTaker.rect.bottom){
            swipeTaker.onSuccessfulSwipe()
            return StartSwipeState()
        }
        return this
    }
}
