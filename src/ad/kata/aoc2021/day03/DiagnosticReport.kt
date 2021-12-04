package ad.kata.aoc2021.day03

import ad.kata.aoc2021.PuzzleInput

class DiagnosticReport(val statusCodes: List<BinaryNumber>) {

    init {
        require(statusCodes.map { it.size() }.distinct().size <= 1)
    }

    val gammaRate: BinaryNumber by lazy {
        val half = statusCodes.size / 2.0

        if (statusCodes.none()) BinaryNumber("0")
        else BinaryNumber(
            statusCodes
                .map(BinaryNumber::toDigitsAsInts)
                .reduce(List<Int>::pairwisePlus)
                .map { if (it >= half) 1 else 0 }
        )
    }

    val epsilonRate: BinaryNumber by lazy { gammaRate.inv() }

    fun powerConsumption() = gammaRate.toInt() * epsilonRate.toInt()
}

fun List<Int>.pairwisePlus(other: List<Int>) = zip(other, Int::plus)

fun diagnosticReportFromInput(filename: String) = DiagnosticReport(
    PuzzleInput(filename).lines().map { BinaryNumber(it) }.toList()
)