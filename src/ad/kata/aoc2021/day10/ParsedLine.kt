package ad.kata.aoc2021.day10

import ad.kata.aoc2021.day10.NavigationLine.Companion.bracketPairs

sealed interface ParsedLine {
    val line: NavigationLine
}

fun NavigationLine.parse() = parseLine(index = 0)

private tailrec fun NavigationLine.parseLine(
    index: Int,
    expectedClosing: List<Char> = emptyList()
): ParsedLine =
    if (!hasIndex(index)) validOrIncomplete(expectedClosing)
    else if (charAt(index) in bracketPairs.keys) parseLine(
        index + 1,
        expectedClosing + bracketPairs.getValue(charAt(index))
    )
    else if (charAt(index) == expectedClosing.lastOrNull()) parseLine(
        index + 1,
        expectedClosing.dropLast(1)
    )
    else CorruptLine(this, unexpectedChar = charAt(index))

private fun NavigationLine.validOrIncomplete(expectedClosing: List<Char>) =
    if (expectedClosing.none()) ValidLine(this)
    else IncompleteLine(this)

data class ValidLine(override val line: NavigationLine) : ParsedLine
data class CorruptLine(override val line: NavigationLine, val unexpectedChar: Char) : ParsedLine
data class IncompleteLine(override val line: NavigationLine) : ParsedLine