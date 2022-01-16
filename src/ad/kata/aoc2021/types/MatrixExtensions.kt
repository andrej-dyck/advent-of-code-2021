package ad.kata.aoc2021.types

import ad.kata.aoc2021.extensions.transposed

fun <T> Matrix<T>.valueAt(index: Coordinate): T? =
    toListOfLists().getOrNull(index.colIndex)?.getOrNull(index.rowIndex)

operator fun Coordinate.plus(other: Pair<Int, Int>) =
    Coordinate(rowIndex + other.first, colIndex + other.second)

fun <T> Matrix<T>.transposed() = toListOfLists().transposed()

fun <T, R> Matrix<T>.mapIndexed(transform: (Coordinate, T) -> R) =
    toListOfLists().mapIndexed { colIndex, c ->
        c.mapIndexed { rowIndex, item -> transform(Coordinate(rowIndex, colIndex), item) }
    }.toMatrix()

fun <T, R> Matrix<T>.map(transform: (value: T) -> R) =
    toListOfLists().map { c -> c.map { item -> transform(item) } }.toMatrix()

fun <T> Matrix<T>.filterIndexed(predicate: (Coordinate, value: T) -> Boolean) =
    toListOfLists().flatMapIndexed { colIndex, c ->
        c.withIndex().filter { item ->
            predicate(Coordinate(item.index, colIndex), item.value)
        }.map {
            Coordinate(it.index, colIndex) to it.value
        }
    }.toMap()

fun <T> Matrix<T>.flatten() = toListOfLists().flatten()

inline fun <T> Matrix<T>.all(predicate: (T) -> Boolean) = flatten().all(predicate)

fun <T> Matrix<T>.adjacentItemsOf(c: Coordinate, vectors: Set<Pair<Int, Int>> = adjacencyVectors) =
    vectors.map { c + it }
        .mapNotNull { ac -> valueAt(ac)?.let { ac to it } }
        .toMap()

private val adjacencyVectors =
    setOf(-1 to -1, 0 to -1, 1 to -1, -1 to 0, 1 to 0, -1 to 1, 0 to 1, 1 to 1)