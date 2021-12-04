package ad.kata.aoc2021.extensions

fun <T> List<List<T>>.transposed() =
    asSequence().transposed().map { it.toList() }
