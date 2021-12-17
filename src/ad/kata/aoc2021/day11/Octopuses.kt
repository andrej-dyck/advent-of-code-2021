package ad.kata.aoc2021.day11

import ad.kata.aoc2021.PuzzleInput
import ad.kata.aoc2021.types.*

class Octopuses(val energyLevels: Matrix<EnergyLevel>) {

    fun countFlashes() =
        energyLevels.flatten().count(EnergyLevel::hasFlashed)

    fun timeProjection() =
        generateSequence(this) { it.increaseEnergyLevels() }.drop(1)

    private fun increaseEnergyLevels() = Octopuses(
        energyLevels.map { e -> e.raiseBy(1) }.propagateFlashes()
    )

    private tailrec fun Matrix<EnergyLevel>.propagateFlashes(
        flashed: Set<Coordinate> = emptySet()
    ): Matrix<EnergyLevel> {
        val newFlashes = filterIndexed { c, e -> e.hasFlashed() && c !in flashed }.keys

        return if (newFlashes.none()) this
        else mapIndexed { c, e -> e.raiseByIf(!e.hasFlashed()) { adjacentFlashes(c, newFlashes) } }
            .propagateFlashes(flashed + newFlashes)
    }

    private fun EnergyLevel.raiseByIf(condition: Boolean, value: () -> Int) =
        if (condition) raiseBy(value()) else this

    private fun Matrix<EnergyLevel>.adjacentFlashes(c: Coordinate, newFlashed: Set<Coordinate>) =
        adjacentItemsOf(c)
            .filterValues(EnergyLevel::hasFlashed).keys
            .intersect(newFlashed).size
}

fun Octopuses.totalFlashesAfter(steps: Int) =
    timeProjection().take(steps).sumOf { it.countFlashes() }

fun octopusesFromInput(filename: String) = Octopuses(
    PuzzleInput(filename).lines()
        .map { it.toCharArray().map(Char::digitToInt) }
        .map { it.map { e -> EnergyLevel(e) } }
        .toMatrix()
)


