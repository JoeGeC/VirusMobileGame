package viruswiper.death

interface DeathUiHandler {
    fun updateAttackValues(newAttackVal: Int, newAttackCost: Int)
    fun updateHealthValues(newHealthVal: Int, newHealthCost: Int)
}
