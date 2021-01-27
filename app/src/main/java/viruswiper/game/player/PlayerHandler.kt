package viruswiper.game.player

interface PlayerHandler {
    fun inflictPlayerDamage(damage: Int)
    fun onPlayerDeath()
}
