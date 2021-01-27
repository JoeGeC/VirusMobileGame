package viruswiper.game.ui

import android.content.Context
import android.graphics.Canvas
import viruswiper.game.GameStats
import viruswiper.game.player.Player

class Ui (context: Context){
    private val border = BorderUi(context)
    private val wave = WaveUi(context, border.bottom)
    private val health = HealthUi(context, border.bottom)
    private val gold = GoldUi(context, border.bottom)
    private val zombieHeart = ZombieHeartUi(context, border.bottom)
    private val ability = AbilityUi(border.bottom)

    fun draw(canvas: Canvas, player: Player, gameStats: GameStats){
        border.draw(canvas)
        wave.draw(canvas, gameStats.getCurrentWave())
        health.draw(canvas, player.currentHealth, player.maxHealth)
        gold.draw(canvas, player.gold)
        zombieHeart.draw(canvas, player.zombieHearts)
        ability.draw(canvas, player.ability)
    }
}