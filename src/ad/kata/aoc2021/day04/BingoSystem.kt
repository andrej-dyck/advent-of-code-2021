package ad.kata.aoc2021.day04

import ad.kata.aoc2021.PuzzleInput
import ad.kata.aoc2021.extensions.headTail
import ad.kata.aoc2021.extensions.toIntsSequence
import ad.kata.aoc2021.nonEmptyLines

class BingoSystem(
    val numbersDrawSequence: Sequence<Int>,
    val blankBoards: List<BingoBoard>
) {
    fun play() =
        numbersDrawSequence
            .runningFold(blankBoards) { boards, n -> boards.map { it.acceptNumber(n) } }
            .drop(1)
}

fun BingoSystem.firstWinningBoard() =
    play().firstNotNullOfOrNull { boards -> boards.firstOrNull { it.isBingo() } }

fun BingoSystem.lastWinningBoard() =
    play().fold(emptyList<BingoBoard>()) { winningBoards, boards ->
        winningBoards + boards
            .filterNot { it in winningBoards }
            .filter { it.isBingo() }
    }.lastOrNull()

fun bingoSystemFromInput(filename: String) =
    PuzzleInput(filename)
        .nonEmptyLines()
        .headTail()
        .let { (numbers, boards) ->
            BingoSystem(
                numbersDrawSequence = numbers.orEmpty().toIntsSequence(),
                blankBoards = boards.chunked(5).map { bingoBoardFromLines(it) }.toList()
            )
        }