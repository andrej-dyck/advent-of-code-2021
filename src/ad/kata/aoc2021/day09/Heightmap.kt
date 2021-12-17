package ad.kata.aoc2021.day09

import ad.kata.aoc2021.PuzzleInput
import ad.kata.aoc2021.types.*

class Heightmap(val heights: Matrix<Height>) {

    fun lowPoints() = heights.filterIndexed { c, h ->
        h.isLowPoint(neighborsOf(c).values)
    }

    private fun Height.isLowPoint(otherHeights: Collection<Height>) =
        otherHeights.all { it > this }

    internal fun neighborsOf(location: Coordinate) =
        heights.adjacentItemsOf(location, neighborhoodVectors)

    companion object {
        private val neighborhoodVectors = setOf(1 to 0, 0 to 1, -1 to 0, 0 to -1)
    }
}

fun Heightmap.assessSmokeRiskLevels() =
    lowPoints().values.assessRiskLevels { Risk(it.unit + 1) }

@JvmInline
value class Height(val unit: Int) : Comparable<Height> {

    operator fun plus(otherHeight: Int) = Height(unit + otherHeight)
    override fun compareTo(other: Height) = unit.compareTo(other.unit)
}

fun heightmapFromInput(filename: String) = Heightmap(
    PuzzleInput(filename).lines()
        .map { l -> l.toCharArray().map { Height(it.digitToInt()) } }
        .toMatrix()
)