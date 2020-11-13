package com.example.virusgame

import android.content.Context
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import kotlin.random.Random

object SoundManager {
    var musicVolume: Int = 70
    var sfxVolume: Int = 70
    private var activePlayers: HashSet<MediaPlayer> = HashSet()
    private var queuedPlayers: HashSet<MediaPlayer> = HashSet()
    private val releaseOnFinishListener =
        OnCompletionListener { player ->
            player.release()
            activePlayers.remove(player)
        }
    private val queueOnFinishListener =
        OnCompletionListener { player ->
            player.release()
            queuedPlayers.remove(player)
            if(queuedPlayers.isNotEmpty()) queuedPlayers.elementAt(0).start()
        }

    fun playSfx(context: Context, soundResource: Int){
        val player = MediaPlayer.create(context, soundResource)
        activePlayers.add(player)
        player.setOnCompletionListener(releaseOnFinishListener)
        player.start()
    }

    fun playQueuedSfx(context: Context, soundResource: Int) {
        val player = MediaPlayer.create(context, soundResource)
        queuedPlayers.add(player)
        player.setOnCompletionListener(queueOnFinishListener)
        if(queuedPlayers.size == 1) player.start()
    }

    fun playRandomSfxOf(context: Context, soundResources: List<Int>){
        playSfx(context, soundResources[Random.nextInt(soundResources.size)])
    }

    fun playMusic(context: Context, soundResource: Int) {
        val player = MediaPlayer.create(context, soundResource)
        activePlayers.add(player)
        player.isLooping = true
        player.start()
    }

    fun pause() {
        for(player in activePlayers) player.pause()
        for(player in queuedPlayers) player.pause()
    }

    fun resume(){
        for(player in activePlayers) player.start()
        if(queuedPlayers.isNotEmpty()) queuedPlayers.elementAt(0).start()
    }
}