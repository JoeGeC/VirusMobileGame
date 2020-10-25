package com.example.virusgame.game.swipestates

import com.example.virusgame.game.SwipeTaker

interface SwipeState {
    fun onTouch(xTouch: Int, yTouch: Int, swipeTaker: SwipeTaker) : SwipeState
}