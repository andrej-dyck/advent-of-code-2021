package ad.kata.aoc2021.day01

class DepthAnalysis(private val sweepReport: SonarSweepReport) {

    fun totalIncreases() =
        sweepReport.depths.asSequence()
            .windowed(2)
            .filter { (d1, d2) -> d1 < d2 }
            .count()
}

