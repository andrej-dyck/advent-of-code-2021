package ad.kata.aoc2021.types

import ad.kata.aoc2021.extensions.headTail

data class Matrix<T>(private val items: List<List<T>>) {

    init {
        require(items.allRowsEqualInSize())
    }

    val dimension by lazy {
        if (items.any()) Dimension(rows = items.first().size, cols = items.size)
        else Dimension(0, 0)
    }

    internal fun toListOfLists() = items

    private fun <T> List<List<T>>.allRowsEqualInSize() =
        headTail().let { (h, t) -> h == null || t.all { it.size == h.size } }

    override fun toString() = "Matrix($items)"
}

data class Dimension(val rows: Int, val cols: Int)

data class Coordinate(val rowIndex: Int, val colIndex: Int) {
    override fun toString() = "($rowIndex, $colIndex)"
}

fun <T> List<List<T>>.toMatrix() = Matrix(this)
fun <T> Sequence<List<T>>.toMatrix() = Matrix(this.toList())
