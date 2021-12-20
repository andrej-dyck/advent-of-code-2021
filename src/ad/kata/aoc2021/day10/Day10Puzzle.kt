package ad.kata.aoc2021.day10

fun main() {
    val navigationProgram = navigationProgramFromInput("day10.input")

    println("-- Day 10: Syntax Scoring --")
    /* part 1 */
    val totalSyntaxCheckerScore = SyntaxCheckerScore(navigationProgram).total()
    println("Total syntax checker score: $totalSyntaxCheckerScore")
    /* part 2 */
    val winningAutocompleteScore = AutocompleteScore(navigationProgram).winner()
    println("Winning autocomplete score: $winningAutocompleteScore")
}