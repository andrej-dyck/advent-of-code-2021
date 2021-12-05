package ad.kata.aoc2021.day04

import ad.kata.aoc2021.extensions.*

class BingoBoard private constructor(
    private val numbers: Map<Int, Coordinate>,
    private val hits: List<Int> = emptyList()
) {

    constructor(boardMatrix: Matrix<Int>) : this(
        boardMatrix
            .mapIndexed { r, c, n -> n to (r to c) }
            .flatten()
            .toMap()
    ) {
        require(boardMatrix.dimension == Dimension(5, 5))
        require(numbers().size == 25)
        require(numbers().all { it >= 0 })
    }

    fun numbers() = numbers.keys
    fun hits() = hits

    fun acceptNumber(drawnNumber: Int) =
        if (drawnNumber !in numbers() || isBingo()) this
        else BingoBoard(numbers, hits + drawnNumber)

    fun isBingo() =
        hits.size >= 5 && marks().let { marks ->
            marks.count5GroupedBy { it.first } || marks.count5GroupedBy { it.second }
        }

    private fun marks() = hits.map { numbers.getValue(it) }

    private fun List<Coordinate>.count5GroupedBy(grouping: (Coordinate) -> Int) =
        groupingBy(grouping).eachCount().any { (_, c) -> c == 5 }

    fun blankCopy() = if(hits.isEmpty()) this else BingoBoard(numbers)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BingoBoard

        if (numbers != other.numbers) return false
        if (hits != other.hits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = numbers.hashCode()
        result = 31 * result + hits.hashCode()
        return result
    }
}

private typealias Coordinate = Pair<Int, Int>

fun bingoBoardFromLines(boardLines: List<String>) = BingoBoard(
    boardLines
        .map(String::trim)
        .map { it.split("\\s+".toRegex()) }
        .map { it.map(String::toInt) }
        .toMatrix()
)