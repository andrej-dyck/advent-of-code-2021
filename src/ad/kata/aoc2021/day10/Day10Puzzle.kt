package ad.kata.aoc2021.day10

fun main() {
    val navigationProgram = navigationProgramFromInput("day10.input")

    println("-- Syntax Scoring --")
    /* part 1 */
    val totalSyntaxCheckerScore = SyntaxCheckerScore(navigationProgram).total()
    println("Total syntax checker score: $totalSyntaxCheckerScore")
}