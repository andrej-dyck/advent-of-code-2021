package ad.kata.aoc2021.day07

import ad.kata.aoc2021.PuzzleInput
import ad.kata.aoc2021.extensions.minMaxOrNull
import ad.kata.aoc2021.extensions.toInts
import kotlin.math.abs

class CrabPositions(val horizontalPositions: List<HorizontalPosition>) {

    fun alignmentCosts() =
        positionValuesRange()
            ?.map { p -> fuelCostTo(p) }
            ?.minOrNull() ?: Fuel(0)

    private fun fuelCostTo(p: HorizontalPosition) =
        Fuel(horizontalPositions.sumOf { it.absDistanceTo(p) })

    private fun positionValuesRange() =
        horizontalPositions.minMaxOrNull()?.let { (min, max) -> min..max }
}

@JvmInline
value class HorizontalPosition(val value: Int) : Comparable<HorizontalPosition> {

    fun absDistanceTo(other: HorizontalPosition) = abs(value - other.value)
    override fun compareTo(other: HorizontalPosition) = value.compareTo(other.value)
}

private fun <R> ClosedRange<HorizontalPosition>.map(transform: (HorizontalPosition) -> R): List<R> =
    (start.value..endInclusive.value).map { transform(HorizontalPosition((it))) }

fun horizontalPositionsOf(positions: List<Int>) =
    positions.map { HorizontalPosition(it) }

fun crabPositionsFromInput(filename: String) = CrabPositions(
    horizontalPositionsOf(
        PuzzleInput(filename).lines().first().toInts()
    )
)