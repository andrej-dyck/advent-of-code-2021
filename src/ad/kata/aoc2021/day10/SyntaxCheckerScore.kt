package ad.kata.aoc2021.day10

class SyntaxCheckerScore(private val program: NavigationProgram) {

    fun total() = program.corruptedLines().sumOf { illegalCharScore[it.unexpectedChar] ?: 0 }

    companion object {
        private val illegalCharScore = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
    }
}