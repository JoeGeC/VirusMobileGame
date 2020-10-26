package com.example.virusgame.game

class Clock {
    fun millisecondsHavePassed(since: Long, milliseconds: Int): Boolean = (System.nanoTime() - since) / 1000000 > milliseconds
}