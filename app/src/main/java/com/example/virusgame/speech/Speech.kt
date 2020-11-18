package com.example.virusgame.speech

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import com.example.virusgame.R
import com.example.virusgame.SpeechSetter
import com.example.virusgame.clock.Clock

class Speech(private val speechView: View): SpeechSetter {
    private var lastCharTime = System.nanoTime()
    private var messageThread: Thread? = null

    override fun setTypedMessage(messageToSet: String){
        initMessage()
        messageThread?.interrupt()
        messageThread = messageThread(messageToSet)
        messageThread?.start()
    }

    private fun initMessage() {
        Handler(Looper.getMainLooper()).postDelayed({
            bounceMessage()
            speechView.visibility = View.VISIBLE
        }, 0)
    }

    private fun messageThread(message: String): Thread {
        var typedMessage = ""
        var charNum = 0
        return Thread {
            while (charNum < message.length && !Thread.currentThread().isInterrupted) {
                if (Clock.haveMillisecondsPassedSince(lastCharTime, 30)) {
                    typedMessage += message[charNum]
                    charNum++
                    lastCharTime = System.nanoTime()
                    setMessage(typedMessage)
                }
            }
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

    override fun setQuickMessage(messageToSet: String){
        setMessage(messageToSet)
        initMessage()
    }
}