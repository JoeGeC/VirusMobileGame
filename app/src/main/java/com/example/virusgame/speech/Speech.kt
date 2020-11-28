package com.example.virusgame.speech

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.virusgame.R
import com.example.virusgame.SoundManager
import com.example.virusgame.clock.Clock
import com.example.virusgame.game.TipListener

class Speech(private val speechView: View, private val tipListener: TipListener): SpeechSetter {
    private var lastCharTime = System.nanoTime()
    private var messageThread: Thread? = null
    private var currentMessage = ""
    private var typing = false
    private var paused = false

    override fun setTypedPauseMessage(messageToSet: String) {
        paused = true
        tipListener.onPauseTipOpen()
        typeMessage(messageToSet)
    }

    override fun setTypedMessage(messageToSet: String){
        paused = false
        typeMessage(messageToSet)
    }

    private fun typeMessage(messageToSet: String){
        initMessage(messageToSet)
        messageThread?.interrupt() //if last message is still typing, stop it
        messageThread = typeOutMessage(messageToSet)
        messageThread?.start()
    }

    private fun initMessage(messageToSet: String) {
        SoundManager.playSfx(speechView.context, R.raw.speech)
        currentMessage = messageToSet
        Handler(Looper.getMainLooper()).postDelayed({
            bounceMessage()
            speechView.visibility = View.VISIBLE
        }, 0)
    }

    private fun typeOutMessage(message: String): Thread {
        var typedMessage = ""
        var charNum = 0
        typing = true
        return Thread {
            while (charNum < message.length && !Thread.currentThread().isInterrupted) {
                if (Clock.haveMillisecondsPassedSince(lastCharTime, 30)) {
                    typedMessage += message[charNum]
                    charNum++
                    lastCharTime = System.nanoTime()
                    setMessage(typedMessage)
                }
            }
            typing = false
        }
    }

    private fun setMessage(messageToSet: String) {
        Handler(Looper.getMainLooper()).postDelayed({
            speechView.findViewById<TextView>(R.id.message).text = messageToSet
        }, 0)
    }

    private fun bounceMessage() {
        val upAnimation = ObjectAnimator.ofFloat(speechView, "translationY", -100f)
        val downAnimation = ObjectAnimator.ofFloat(speechView, "translationY", 0f)
        Handler(Looper.getMainLooper()).postDelayed({
            AnimatorSet().apply {
                play(upAnimation).before(downAnimation)
                start()
            }
        }, 0)
    }

    override fun setQuickPauseMessage(messageToSet: String) {
        paused = true
        tipListener.onPauseTipOpen()
        quickMessage(messageToSet)
    }

    override fun setQuickMessage(messageToSet: String){
        paused = false
        quickMessage(messageToSet)
    }

    private fun quickMessage(messageToSet: String) {
        setMessage(messageToSet)
        initMessage(messageToSet)
    }

    override fun onClick(speechView: ConstraintLayout) {
        if(typing) {
            setMessage(currentMessage)
            messageThread?.interrupt()
            return
        }
        if(paused) tipListener.onPauseTipClosed()
        closeMessage()
    }

    override fun closeMessage() {
        speechView.visibility = View.GONE
    }
}