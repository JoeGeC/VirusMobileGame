package com.example.virusgame.game.events

import com.example.virusgame.MainActivity
import com.example.virusgame.game.Speech

abstract class Event {
    lateinit var speech: Speech
    val context = MainActivity.applicationContext()
    var complete = false
    abstract fun trigger()
}