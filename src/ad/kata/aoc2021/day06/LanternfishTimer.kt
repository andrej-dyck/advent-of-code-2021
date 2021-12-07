package ad.kata.aoc2021.day06

@JvmInline
value class LanternfishTimer(private val daysUntilReproduction: Int) {

    init {
        require(daysUntilReproduction >= 0)
    }

    internal fun tick(daysCycle: Int) = LanternfishTimer(
        (daysUntilReproduction - 1).ifNegative { daysCycle - 1 }
    )

    companion object {
        val Zero = LanternfishTimer(0)
    }
}

private inline fun Int.ifNegative(otherwise: (Int) -> Int) = takeIf { it >= 0 } ?: otherwise(this)

fun lanternfishTimersOf(vararg daysUntilReproduction: Int) =
    daysUntilReproduction.asSequence().map { LanternfishTimer(it) }