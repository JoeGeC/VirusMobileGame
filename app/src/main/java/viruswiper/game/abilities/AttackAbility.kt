package viruswiper.game.abilities

import viruswiper.game.zombie.ZombieDamageHandler

abstract class AttackAbility(private var zombieDamageHandler: ZombieDamageHandler) : Ability(zombieDamageHandler.context){
    abstract val attackModifier: Float

    override fun effect() {
        zombieDamageHandler.inflictZombieDamage((zombieDamageHandler.getPlayerAttack() * attackModifier).toInt())
    }
}