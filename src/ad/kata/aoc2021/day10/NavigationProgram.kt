package ad.kata.aoc2021.day10

import ad.kata.aoc2021.PuzzleInput

class NavigationProgram(val lines: Sequence<NavigationLine>) {

    constructor(vararg lines: String) : this(lines.asSequence().map { NavigationLine(it) })

    val parsedLines by lazy { lines.map(NavigationLine::parse) }
}

fun NavigationProgram.corruptedLines() = parsedLines.filterIsInstance<CorruptLine>()

fun navigationProgramFromInput(filename: String) = NavigationProgram(
    PuzzleInput(filename).lines().map { NavigationLine(it) }
)