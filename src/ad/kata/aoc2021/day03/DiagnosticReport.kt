package ad.kata.aoc2021.day03

import ad.kata.aoc2021.PuzzleInput
import ad.kata.aoc2021.extensions.transposed

class DiagnosticReport(val statusCodes: List<BinaryNumber>) {

    val gammaRate: BinaryNumber by lazy {
        val half = statusCodes.size / 2.0

        BinaryNumber(
            statusCodes.map(BinaryNumber::toDigitsAsIntList)
                .reduceCols(List<Int>::sum)
                .map { if (it >= half) 1 else 0 }
                .ifEmpty { listOf(0) }
        )
    }

    val epsilonRate: BinaryNumber by lazy { gammaRate.inv() }

    fun powerConsumption() = gammaRate.toInt() * epsilonRate.toInt()
}

private fun <T, R> List<List<T>>.reduceCols(transform: (List<T>) -> R): List<R> =
    transposed().map(transform)

fun diagnosticReportFromInput(filename: String) = DiagnosticReport(
    PuzzleInput(filename).lines().map { BinaryNumber(it) }.toList()
)