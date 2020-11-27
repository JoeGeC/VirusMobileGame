package com.example.virusgame.speech

import androidx.constraintlayout.widget.ConstraintLayout

interface SpeechSetter {
    fun setTypedPauseMessage(messageToSet: String)
    fun setTypedMessage(messageToSet: String)
    fun setQuickPauseMessage(messageToSet: String)
    fun setQuickMessage(messageToSet: String)
    fun closeMessage()
    fun onClick(speechView: ConstraintLayout)
}
