package com.example.virusgame.clock

object Clock {
    fun millisecondsHavePassedSince(since: Long, milliseconds: Int): Boolean = (System.nanoTime() - since) / 1000000 > milliseconds
}