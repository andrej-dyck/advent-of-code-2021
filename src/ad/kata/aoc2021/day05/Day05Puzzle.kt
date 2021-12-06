package ad.kata.aoc2021.day05

fun main() {
    val hydrothermalVentsField = hydrothermalVentsFromInput("day05.input")

    println("--Hydrothermal Venture --")
    /* part 1 */
    val pointsOfOverlaps = hydrothermalVentsField.filterNoDiagonals().pointsOfOverlaps()
    println("number of points where at least 2 lines (horizontal or vertical) overlap: ${pointsOfOverlaps.size}")
    /* part 2 */
    val pointsOfOverlapsWithDiagonals = hydrothermalVentsField.pointsOfOverlaps()
    println("number of points where at least 2 lines overlap: ${pointsOfOverlapsWithDiagonals.size}")
}