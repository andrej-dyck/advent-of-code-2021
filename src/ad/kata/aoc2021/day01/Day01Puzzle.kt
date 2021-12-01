package ad.kata.aoc2021.day01

fun main() {
    val depthAnalysis = DepthAnalysis(
        sonarSweepReportFromInput("day01.input")
    )

    println("-- Depth Analysis --")
    /* part 1 */
    println("number of total depth measurement increases: ${depthAnalysis.totalIncreases()}")
    /* part 2 */
    println("number of depth measurement increases after smoothing: ${depthAnalysis.totalIncreasesAfterSmoothing()}")
}