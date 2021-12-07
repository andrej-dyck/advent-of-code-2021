package ad.kata.aoc2021.day06

import ad.kata.aoc2021.PuzzleInput
import ad.kata.aoc2021.extensions.plusNotNull
import ad.kata.aoc2021.extensions.toIntsSequence
import ad.kata.aoc2021.extensions.toMapMerging

data class LanternfishPopulation(val state: Map<LanternfishTimer, Amount>) {

    private constructor(stateEntries: List<Pair<LanternfishTimer, Amount>>)
        : this(stateEntries.toMapMerging(List<Amount>::sum))

    constructor(timers: Sequence<LanternfishTimer>) : this(
        timers.groupingBy { it }.eachCount().mapValues { Amount(it.value) }
    )

    fun totalAmount() = state.values.fold(Amount(0), Amount::plus)

    fun nextDay() = LanternfishPopulation(
        state
            .map { it.key.tick(daysCycle = SEVEN_DAYS) to it.value }
            .plusNotNull(newLanternfish())
    )

    private fun newLanternfish() =
        state[LanternfishTimer.Zero]?.let { amount ->
            LanternfishTimer(SEVEN_DAYS + 1) to amount
        }

    companion object {
        private const val SEVEN_DAYS = 7
    }
}

fun LanternfishPopulation.simulateDays() =
    generateSequence(this) { it.nextDay() }

fun LanternfishPopulation.afterDays(days: Int) =
    simulateDays().take(days + 1).last()

@JvmInline
value class Amount internal constructor(val value: Int) {

    init {
        require(value >= 0)
    }

    operator fun plus(other: Amount) = Amount(value + other.value)
}

internal fun List<Amount>.sum() = Amount(sumOf { it.value })

fun lanternfishPopulationFromInput(filename: String) = LanternfishPopulation(
    PuzzleInput(filename).lines().first()
        .toIntsSequence()
        .map { LanternfishTimer(it) }
)
