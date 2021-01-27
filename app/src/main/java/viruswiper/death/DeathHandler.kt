package viruswiper.death

import viruswiper.MenuListener


interface DeathHandler : MenuListener {
    fun upgradeAttack()
    fun upgradeHealth()
    fun revive()
    fun setDeathUiHandler(handler: DeathUiHandler)
}