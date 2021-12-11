package ad.kata.aoc2021.day08

import ad.kata.aoc2021.PuzzleInput
import ad.kata.aoc2021.extensions.splitTrim

class DisplayTroubleshooting(val entries: List<TroubleshootingEntry>) {

    constructor(vararg entries: TroubleshootingEntry) : this(entries.toList())

    fun deducedOutputValues() = entries.map { it.deduceOutputValue() }
}

data class TroubleshootingEntry(
    val uniqueSignals: List<Signal>,
    val outputSignals: List<Signal>
) {

    private val deducedOutputDigits by lazy {
        SignalDeduction(uniqueSignals).deduceDigits(outputSignals)
    }

    fun deduceOutputValue() =
        if (deducedOutputDigits.any { it == null }) null
        else deducedOutputDigits.filterNotNull().toInt()
}

fun troubleShootingFromInput(filename: String) = DisplayTroubleshooting(
    PuzzleInput(filename).lines()
        .map(String::parseTroubleshootingEntry)
        .toList()
)

private fun String.parseTroubleshootingEntry() =
    splitTrim('|')
        .map { signals -> listOfSignals(signals, delimiter = ' ') }
        .let { (sp, ov) -> TroubleshootingEntry(uniqueSignals = sp, outputSignals = ov) }

fun listOfSignals(segments: String, delimiter: Char = ' ') =
    segments.split(delimiter).map { Signal(it) }
