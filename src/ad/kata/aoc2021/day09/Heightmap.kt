package ad.kata.aoc2021.day09

import ad.kata.aoc2021.PuzzleInput
import ad.kata.aoc2021.types.*

class Heightmap(val heights: Matrix<Height>) {

    fun lowPoints() = heights.filterIndexed { c, h ->
        h.isLowPoint(neighborsOf(c))
    }.map { it.second }

    private fun neighborsOf(c: Coordinate) =
        neighborhoodVectors.map { c.plus(it) }.mapNotNull { heights.valueAt(it) }

    private operator fun Coordinate.plus(other: Pair<Int, Int>) =
        Coordinate(rowIndex + other.first, colIndex + other.second)

    companion object {
        private val neighborhoodVectors = setOf(1 to 0, 0 to 1, -1 to 0, 0 to -1)
    }
}

fun Heightmap.assessSmokeRiskLevels() =
    lowPoints().assessRiskLevels { Risk(it.unit + 1) }

@JvmInline
value class Height(val unit: Int) : Comparable<Height> {
    override fun compareTo(other: Height) = unit.compareTo(other.unit)
}

fun Height.isLowPoint(otherHeights: List<Height>) =
    otherHeights.all { it > this }

fun heightmapFromInput(filename: String) = Heightmap(
    PuzzleInput(filename).lines()
        .map { l -> l.toCharArray().map { Height(it.digitToInt()) } }
        .toList()
        .toMatrix()
)