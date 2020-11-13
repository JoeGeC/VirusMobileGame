package com.example.virusgame

import android.content.Context
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import kotlin.math.ln
import kotlin.random.Random

object SoundManager {
    private var activeMusicPlayer: MediaPlayer = MediaPlayer()
    private var activeSfxPlayers: HashSet<MediaPlayer> = HashSet()
    private var queuedSfxPlayers: HashSet<MediaPlayer> = HashSet()

    var musicVolume: Float = 0.7f
        set(value){
            field = value
            activeMusicPlayer.setVolume(value, value)
        }

    var sfxVolume: Float = 0.7f
        set(value){
            field = value
            for(player in activeSfxPlayers) player.setVolume(value, value)
            for(player in queuedSfxPlayers) player.setVolume(value, value)
        }

    private val releaseOnFinishListener =
        OnCompletionListener { player ->
            player.release()
            activeSfxPlayers.remove(player)
        }

    private val queueOnFinishListener =
        OnCompletionListener { player ->
            player.release()
            queuedSfxPlayers.remove(player)
            if(queuedSfxPlayers.isNotEmpty()) queuedSfxPlayers.elementAt(0).start()
        }

    fun playSfx(context: Context, soundResource: Int){
        val player = MediaPlayer.create(context, soundResource)
        activeSfxPlayers.add(player)
        player.setOnCompletionListener(releaseOnFinishListener)
        player.setVolume(sfxVolume, sfxVolume)
        player.start()
    }

    fun playQueuedSfx(context: Context, soundResource: Int) {
        val player = MediaPlayer.create(context, soundResource)
        queuedSfxPlayers.add(player)
        player.setOnCompletionListener(queueOnFinishListener)
        player.setVolume(sfxVolume, sfxVolume)
        if(queuedSfxPlayers.size == 1) player.start()
    }

    fun playRandomSfxOf(context: Context, soundResources: List<Int>){
        playSfx(context, soundResources[Random.nextInt(soundResources.size)])
    }

    fun playMusic(context: Context, soundResource: Int) {
        val player = MediaPlayer.create(context, soundResource)
        activeMusicPlayer = player
        player.isLooping = true
        player.setVolume(musicVolume, musicVolume)
        player.start()
    }

    fun pause() {
        for(player in activeSfxPlayers) player.pause()
        for(player in queuedSfxPlayers) player.pause()
        activeMusicPlayer.pause()
    }

    fun resume(){
        for(player in activeSfxPlayers) player.start()
        if(queuedSfxPlayers.isNotEmpty()) queuedSfxPlayers.elementAt(0).start()
        activeMusicPlayer.start()
    }
}