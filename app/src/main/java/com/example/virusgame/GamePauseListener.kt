package com.example.virusgame

interface GamePauseListener {
    fun gamePause()
    fun canGameResume(): Boolean
}
