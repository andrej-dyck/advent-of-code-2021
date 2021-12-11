package ad.kata.aoc2021.day09

fun main() {
    val heightmap = heightmapFromInput("day09.input")

    println("-- Smoke Basin Risk Assessment --")
    /* part 1 */
    val smokeRiskLevels = heightmap.assessSmokeRiskLevels()
    println("Total risk level of smoke at low points: ${smokeRiskLevels.total()}")
}