package com.example.virusgame.game.rotation

interface RotationHandler {
    fun onRotate(pitch: Double, tilt: Double, azimuth: Double)
}
