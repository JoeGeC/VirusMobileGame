package viruswiper.clock

object Clock {
    fun haveMillisecondsPassedSince(since: Long, milliseconds: Int): Boolean = millisecondsPassedSince(since) > milliseconds
    fun millisecondsPassedSince(since: Long): Long = (System.nanoTime() - since) / 1000000
}