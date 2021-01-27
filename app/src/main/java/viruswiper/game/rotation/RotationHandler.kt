package viruswiper.game.rotation

interface RotationHandler {
    fun onRotate(pitch: Double, tilt: Double, azimuth: Double)
}
