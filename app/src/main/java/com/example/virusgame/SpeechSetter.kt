package com.example.virusgame

interface SpeechSetter {
    fun setTypedMessage(messageToSet: String)
    fun setQuickMessage(messageToSet: String)
    fun closeMessage()
}
