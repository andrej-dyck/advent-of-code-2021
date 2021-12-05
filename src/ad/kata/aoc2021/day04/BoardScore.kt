package ad.kata.aoc2021.day04

fun BingoBoard.score() =
    if (isBingo()) unmarkedNumbers().sum() * hits().last()
    else 0

private fun BingoBoard.unmarkedNumbers() = numbers() - hits().toSet()