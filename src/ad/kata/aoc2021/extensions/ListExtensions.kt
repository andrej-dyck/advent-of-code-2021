package ad.kata.aoc2021.extensions

fun <T> List<T>.headTail() =
    asSequence().headTail().let { it.first to it.second.toList() }

fun <T> List<List<T>>.transposed() =
    asSequence().transposed().map { it.toList() }
