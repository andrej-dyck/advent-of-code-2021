package ad.kata.aoc2021.day01

class DepthAnalysis private constructor(private val depthsSequence: Sequence<Int>) {

    constructor(sweepReport: SonarSweepReport) : this(sweepReport.depths.asSequence())

    fun totalIncreases() = depthsSequence.countIncreases()

    fun totalIncreasesAfterSmoothing(smoothingWindow: Int = 3) =
        depthsSequence
            .windowed(smoothingWindow) { it.sum() }
            .countIncreases()

    private fun Sequence<Int>.countIncreases() =
        windowed(2).count { (d1, d2) -> d1 < d2 }
}

