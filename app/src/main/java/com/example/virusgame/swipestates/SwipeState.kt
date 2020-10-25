package com.example.virusgame.swipestates

import com.example.virusgame.SwipeTaker

interface SwipeState {
    fun onTouch(xTouch: Int, yTouch: Int, swipeTaker: SwipeTaker) : SwipeState
}