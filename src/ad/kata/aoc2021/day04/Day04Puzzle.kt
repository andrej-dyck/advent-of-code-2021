package ad.kata.aoc2021.day04

fun main() {
    val bingoSystem = bingoSystemFromInput("day04.input")

    println("-- Giant Squid Bingo --")
    /* part 1 */
    val firstWinningBoard = bingoSystem.firstWinningBoard()
    println("score of first winning board: ${firstWinningBoard?.score() ?: 0}")
    val loosingBoard = bingoSystem.lastWinningBoard()
    println("score of last winning board: ${loosingBoard?.score() ?: 0}")
}
