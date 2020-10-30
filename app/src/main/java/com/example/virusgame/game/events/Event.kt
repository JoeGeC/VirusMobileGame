package com.example.virusgame.game.events

abstract class Event {
    var complete = false
    abstract fun trigger()
}