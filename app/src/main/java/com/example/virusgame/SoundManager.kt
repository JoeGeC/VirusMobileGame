package com.example.virusgame

import android.content.Context
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import kotlin.random.Random

object SoundManager {
    private var activePlayers: HashSet<MediaPlayer> = HashSet()
    private val releaseOnFinishListener =
        OnCompletionListener { player ->
            player.release()
            activePlayers.remove(player)
        }

    fun playEffect(context: Context, soundResource: Int){
        val player = MediaPlayer.create(context, soundResource)
        activePlayers.add(player)
        player.setOnCompletionListener(releaseOnFinishListener)
        player.start()
    }

    fun playRandomOf(context: Context, soundResources: List<Int>){
        playEffect(context, soundResources[Random.nextInt(soundResources.size)])
    }

    fun playMusic(context: Context, soundResource: Int) {
        val player = MediaPlayer.create(context, soundResource)
        activePlayers.add(player)
        player.isLooping = true
        player.start()
    }
}