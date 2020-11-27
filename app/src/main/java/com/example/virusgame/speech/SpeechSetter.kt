package com.example.virusgame.speech

import androidx.constraintlayout.widget.ConstraintLayout

interface SpeechSetter {
    fun setTypedMessage(messageToSet: String)
    fun setQuickMessage(messageToSet: String)
    fun closeMessage()
    fun onClick(speechView: ConstraintLayout)
}
