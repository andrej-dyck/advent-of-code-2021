package ad.kata.aoc2021.day08

/**
 * Seven-segment Display default Signal-Patterns
 *
 *   0:      1:      2:      3:      4:
 *  aaaa    ....    aaaa    aaaa    ....
 * b    c  .    c  .    c  .    c  b    c
 * b    c  .    c  .    c  .    c  b    c
 *  ....    ....    dddd    dddd    dddd
 * e    f  .    f  e    .  .    f  .    f
 * e    f  .    f  e    .  .    f  .    f
 *  gggg    ....    gggg    gggg    ....
 *
 *   5:      6:      7:      8:      9:
 *  aaaa    aaaa    aaaa    aaaa    aaaa
 * b    .  b    .  .    c  b    c  b    c
 * b    .  b    .  .    c  b    c  b    c
 *  dddd    dddd    ....    dddd    dddd
 * .    f  e    f  .    f  e    f  .    f
 * .    f  e    f  .    f  e    f  .    f
 *  gggg    gggg    ....    gggg    gggg
 */
data class Signal(val segments: Set<Char>) {

    init {
        require(segments.all { it in 'a'..'g' })
    }

    constructor(segments: String) : this(segments.toCharArray().toHashSet())

    constructor(digit: Digit) : this(
        when (digit.value) {
            1 -> "cf"
            2 -> "acdeg"
            3 -> "acdfg"
            4 -> "bcdf"
            5 -> "abdfg"
            6 -> "abdefg"
            7 -> "acf"
            8 -> "abcdefg"
            9 -> "abcdfg"
            else -> "abcefg" // 0
        }
    )

    val segmentsCount = segments.size
}

