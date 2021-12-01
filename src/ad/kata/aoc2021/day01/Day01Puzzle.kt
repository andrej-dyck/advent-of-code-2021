package ad.kata.aoc2021.day01

fun main() {
    /* part 1 */
    val totalIncreases = DepthAnalysis(
        sonarSweepReportFromInput("day01.input")
    ).totalIncreases()

    println("-- Depth Analysis --")
    println("number of times a depth measurement increases: $totalIncreases")
}