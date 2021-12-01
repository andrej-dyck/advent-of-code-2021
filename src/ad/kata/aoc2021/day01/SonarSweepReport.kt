package ad.kata.aoc2021.day01

import ad.kata.aoc2021.PuzzleInput

data class SonarSweepReport(val depths: List<Int>)

fun sonarSweepReportFromInput(filename: String) = SonarSweepReport(
    PuzzleInput(filename).lines().map { it.toInt() }.toList()
)