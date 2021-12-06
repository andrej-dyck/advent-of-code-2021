package ad.kata.aoc2021.day05

import ad.kata.aoc2021.PuzzleInput

class HydrothermalVentsField(val linesOfVents: Set<LineSegment>) {

    fun pointsOfOverlaps() =
        linesOfVents.asSequence()
            .flatMap(LineSegment::points)
            .groupingBy { it }.eachCount()
            .filterValues { it >= 2 }
            .keys
}

fun HydrothermalVentsField.filterNoDiagonals() = HydrothermalVentsField(
    linesOfVents.filter { it.isHorizontal() || it.isVertical() }.toHashSet()
)

fun hydrothermalVentsFromInput(filename: String) =
    HydrothermalVentsField(
        PuzzleInput(filename).lines().map { parseLineSegment(it) }.toHashSet()
    )
