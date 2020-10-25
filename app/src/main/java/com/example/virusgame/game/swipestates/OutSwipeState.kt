package com.example.virusgame.game.swipestates

import com.example.virusgame.game.SwipeTaker

class OutSwipeState : SwipeState {
    override fun onTouch(xTouch: Int, yTouch: Int, swipeTaker: SwipeTaker): SwipeState {
        if(xTouch < swipeTaker.rect.right && xTouch > swipeTaker.rect.left && yTouch > swipeTaker.rect.top && yTouch < swipeTaker.rect.bottom){
            return InsideSwipeState(xTouch, yTouch)
        }
        return this
    }
}
