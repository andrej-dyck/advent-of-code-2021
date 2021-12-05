package ad.kata.aoc2021.extensions

class Matrix<T>(private val values: List<List<T>>) {

    init {
        require(values.allRowsEqualInSize())
    }

    val dimension by lazy {
        if (values.any()) Dimension(rows = values.first().size, cols = values.size)
        else Dimension(0, 0)
    }

    fun toListOfLists() = values

    private fun <T> List<List<T>>.allRowsEqualInSize() =
        headTail().let { (h, t) -> h == null || t.all { it.size == h.size } }
}

data class Dimension(val rows: Int, val cols: Int)

fun <T> Matrix<T>.transposed() = toListOfLists().transposed()

fun <T, R> Matrix<T>.mapIndexed(transform: (rowIndex: Int, colIndex: Int, value: T) -> R) =
    toListOfLists().mapIndexed { colIndex, c ->
        c.mapIndexed { rowIndex, v -> transform(colIndex, rowIndex, v) }
    }.let { Matrix(it) }

fun <T> Matrix<T>.flatten() = toListOfLists().flatten()