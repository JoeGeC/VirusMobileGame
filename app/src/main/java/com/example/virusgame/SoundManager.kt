package com.example.virusgame

import android.content.Context
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener

object SoundManager {
    private var activePlayers: HashSet<MediaPlayer> = HashSet()
    private val releaseOnFinishListener =
        OnCompletionListener { player ->
            player.release()
            activePlayers.remove(player)
        }

    fun play(context: Context, soundResource: Int){
        val player = MediaPlayer.create(context, soundResource)
        activePlayers.add(player)
        player.setOnCompletionListener(releaseOnFinishListener)
        player.start()
    }
}