package ad.kata.aoc2021.day07

import ad.kata.aoc2021.PuzzleInput
import ad.kata.aoc2021.extensions.minMaxOrNull
import ad.kata.aoc2021.extensions.toInts

class CrabPositions(val horizontalPositions: List<HorizontalPosition>) {

    fun alignmentCosts() =
        positionValuesRange()
            ?.map { fuelCostTo(it, ::sum0ToN) }
            ?.minOrNull() ?: Fuel(0)

    private fun fuelCostTo(p: HorizontalPosition, fuelAmountForDistance: (Int) -> Int) =
        Fuel(horizontalPositions.sumOf { fuelAmountForDistance(it.absDistanceTo(p)) })

    private fun positionValuesRange() =
        horizontalPositions.minMaxOrNull()?.let { (min, max) -> min..max }
}

fun sum0ToN(n: Int) = (n * (n + 1)) / 2 // gauss summation

private fun <R> ClosedRange<HorizontalPosition>.map(transform: (HorizontalPosition) -> R) =
    (start.value..endInclusive.value).map { transform(HorizontalPosition((it))) }

fun crabPositionsFromInput(filename: String) = CrabPositions(
    horizontalPositionsOf(
        PuzzleInput(filename).lines().first().toInts()
    )
)