package ad.kata.aoc2021.day08

class SignalDeduction(
    uniqueSignals: List<Signal>
) {

    fun deduceDigits(signals: List<Signal>) =
        signals.map { deducedSignalToDigits[it] }

    private val deducedSignalToDigits: Map<Signal, Digit> by lazy {
        uniqueLengthDigitsIn(uniqueSignals)
            .withIdentifiedSize5Digits(uniqueSignals.filter { it.segmentsCount == 5 })
            .withIdentifiedSize6Digits(uniqueSignals.filter { it.segmentsCount == 6 })
            .toKnownSignals()
    }

    /**
     *    1:      4:      7:      8:
     *   ....    ....    aaaa    aaaa
     *  .    c  b    c  .    c  b    c
     *  .    c  b    c  .    c  b    c
     *   ....    dddd    ....    dddd
     *  .    f  .    f  .    f  e    f
     *  .    f  .    f  .    f  e    f
     *   ....    ....    ....    gggg
     */
    private fun uniqueLengthDigitsIn(signals: List<Signal>) =
        uniqueSegmentsLengthDigits.mapValues { (_, length) ->
            signals.firstOrNull { it.segmentsCount == length }
        }

    /**
     *    2:      3:      5:
     *   aaaa    aaaa    aaaa
     *  .    c  .    c  b    .
     *  .    c  .    c  b    .
     *   dddd    dddd    dddd
     *  e    .  .    f  .    f
     *  e    .  .    f  .    f
     *   gggg    gggg    gggg
     */
    private fun Map<Digit, Signal?>.withIdentifiedSize5Digits(size5Signals: List<Signal>): Map<Digit, Signal?> {
        val signalFor2 = size5Signals.firstIntersectionOrNull(get(Digit(4))) { it.size == 2 }
        val signalFor3 = size5Signals.firstIntersectionOrNull(get(Digit(7))) { it.size == 3 }

        return this + mapOf(
            Digit(2) to signalFor2,
            Digit(3) to signalFor3,
            Digit(5) to size5Signals.firstOrNull { it != signalFor2 && it != signalFor3 }
        )
    }

    private inline fun List<Signal>.firstIntersectionOrNull(
        signal: Signal?,
        predicate: (Set<Char>) -> Boolean
    ) =
        signal?.let { s -> firstOrNull { predicate(it.segments.intersect(s.segments)) } }

    /**
     *    0:      6:      9:
     *   aaaa    aaaa    aaaa
     *  b    c  b    .  b    c
     *  b    c  b    .  b    c
     *   ....    dddd    dddd
     *  e    f  e    f  .    f
     *  e    f  e    f  .    f
     *   gggg    gggg    gggg
     */
    private fun Map<Digit, Signal?>.withIdentifiedSize6Digits(size6Signals: List<Signal>): Map<Digit, Signal?> {
        val signalFor6 = size6Signals.firstIntersectionOrNull(get(Digit(7))) { it.size == 2 }
        val signalFor9 = size6Signals.firstIntersectionOrNull(get(Digit(3))) { it.size == 5 }

        return this + mapOf(
            Digit(0) to size6Signals.firstOrNull { it != signalFor6 && it != signalFor9 },
            Digit(6) to signalFor6,
            Digit(9) to signalFor9
        )
    }

    private fun Map<Digit, Signal?>.toKnownSignals() =
        mapNotNull { (d, s) -> s?.let { it to d } }.toMap()

    companion object {
        private val uniqueSegmentsLengthDigits by lazy {
            setOf(1, 4, 7, 8)
                .map { Digit(it) }
                .associateWith { Signal(it).segmentsCount }
        }
    }
}
