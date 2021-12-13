package ad.kata.aoc2021.day09

import ad.kata.aoc2021.extensions.product

fun main() {
    val heightmap = heightmapFromInput("day09.input")

    println("-- Smoke Basin Risk Assessment --")
    /* part 1 */
    val smokeRiskLevels = heightmap.assessSmokeRiskLevels()
    println("Total risk level of smoke at low points: ${smokeRiskLevels.total()}")
    /* part 2 */
    val largestBasinsSizes = heightmap.basins().takeLargest(3).map { it.size }
    println("Product of sizes of the three largest basins: ${largestBasinsSizes.product()}")
}