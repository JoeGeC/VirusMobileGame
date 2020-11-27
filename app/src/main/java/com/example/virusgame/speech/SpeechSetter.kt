package com.example.virusgame.speech

interface SpeechSetter {
    fun setTypedMessage(messageToSet: String)
    fun setQuickMessage(messageToSet: String)
    fun closeMessage()
}
