package ad.kata.aoc2021.day08

import ad.kata.aoc2021.PuzzleInput
import ad.kata.aoc2021.extensions.splitTrim

class DisplayTroubleshooting(val entries: List<TroubleshootingEntry>) {

    constructor(vararg entries: TroubleshootingEntry) : this(entries.toList())
}

fun DisplayTroubleshooting.identifiedOutputDigits() =
    entries.flatMap { it.identifiedDigits() }.filterNotNull()

data class TroubleshootingEntry(
    val uniqueSignalPatterns: List<SegmentsDigit>,
    val outputValues: List<SegmentsDigit>
) {

    fun identifiedDigits() = outputValues.map(::identifiedDigit)

    private fun identifiedDigit(digit: SegmentsDigit) =
        uniqueSegmentsLengthDigits[digit.count()]

    companion object {
        private val uniqueSegmentsLengthDigits by lazy {
            setOf(1, 4, 7, 8)
                .map { Digit(it) }
                .associateBy { SegmentsDigit(it).count() }
        }
    }
}

fun troubleShootingFromInput(filename: String) = DisplayTroubleshooting(
    PuzzleInput(filename).lines()
        .map(String::parseTroubleshootingEntry)
        .toList()
)

fun String.parseTroubleshootingEntry() =
    splitTrim('|')
        .map { segments -> segments.split(' ').map { SegmentsDigit(it) } }
        .let { (sp, ov) -> TroubleshootingEntry(uniqueSignalPatterns = sp, outputValues = ov) }