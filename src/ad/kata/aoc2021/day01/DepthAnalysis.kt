package ad.kata.aoc2021.day01

class DepthAnalysis(private val depthsSequence: Sequence<DepthMeasurement>) {

    constructor(sweepReport: SonarSweepReport) : this(sweepReport.depths)

    fun totalIncreases() = depthsSequence.countIncreases()

    fun totalIncreasesAfterSmoothing(smoothingWindow: Int = 3) =
        depthsSequence
            .windowed(smoothingWindow) { it.sum() }
            .countIncreases()

    private fun Sequence<DepthMeasurement>.countIncreases() =
        windowed(2).count { (d1, d2) -> d1.value < d2.value }
}

@JvmInline
value class DepthMeasurement(val value: Int)

fun List<DepthMeasurement>.sum() =
    DepthMeasurement(sumOf { it.value })