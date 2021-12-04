package ad.kata.aoc2021.day03

import ad.kata.aoc2021.PuzzleInput
import ad.kata.aoc2021.extensions.transposed

class DiagnosticReport(val statusCodes: List<BinaryNumber>) {

    val gammaRate: BinaryNumber by lazy { mostCommonBits(statusCodes) }
    val epsilonRate: BinaryNumber by lazy { gammaRate.inv() }

    val oxygenGeneratorRating: BinaryNumber? by lazy {
        findRatingValue(statusCodes) { mostCommonBits(it) }
    }
    val cO2ScrubberRating: BinaryNumber? by lazy {
        findRatingValue(statusCodes) { leastCommonBits(it) }
    }

    private fun mostCommonBits(codes: List<BinaryNumber>): BinaryNumber {
        val half = codes.size / 2.0
        return BinaryNumber(
            codes.map(BinaryNumber::toDigitsAsIntList)
                .reduceCols(List<Int>::sum)
                .map { if (it >= half) 1 else 0 }
                .ifEmpty { listOf(0) }
        )
    }

    private fun <T, R> List<List<T>>.reduceCols(transform: (List<T>) -> R): List<R> =
        transposed().map(transform)

    private fun leastCommonBits(codes: List<BinaryNumber>) =
        mostCommonBits(codes).inv()

    private tailrec fun findRatingValue(
        codes: List<BinaryNumber>,
        pos: Int = 0,
        prevSize: Int = 0,
        bitsMask: (List<BinaryNumber>) -> BinaryNumber
    ): BinaryNumber? =
        if (codes.size <= 1 || codes.size == prevSize)
            codes.firstOrNull()
        else {
            val sigBit = bitsMask(codes).bitFromLeft(pos)
            val filtered = codes.filter { sigBit == it.bitFromLeft(pos) }
            findRatingValue(filtered, pos = pos + 1, prevSize = codes.size, bitsMask)
        }

    private fun BinaryNumber.bitFromLeft(index: Int) = toString().getOrNull(index)
}

fun DiagnosticReport.powerConsumption() =
    gammaRate.toInt() * epsilonRate.toInt()

fun DiagnosticReport.liveSupportRating() =
    (oxygenGeneratorRating?.toInt() ?: 0) * (cO2ScrubberRating?.toInt() ?: 0)

fun diagnosticReportFromInput(filename: String) = DiagnosticReport(
    PuzzleInput(filename).lines().map { BinaryNumber(it) }.toList()
)