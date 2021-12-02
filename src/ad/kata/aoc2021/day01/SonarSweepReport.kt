package ad.kata.aoc2021.day01

import ad.kata.aoc2021.PuzzleInput

class SonarSweepReport(val depths: Sequence<DepthMeasurement>)

fun sonarSweepReportFromInput(filename: String) = SonarSweepReport(
    PuzzleInput(filename).lines()
        .map { DepthMeasurement(it.toInt()) }
)