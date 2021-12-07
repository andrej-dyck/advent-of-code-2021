package ad.kata.aoc2021.extensions

fun <T> List<T>.headTail() =
    asSequence().headTail().let { it.first to it.second.toList() }

fun <T> List<List<T>>.transposed() =
    asSequence().transposed().map { it.toList() }

fun <T> List<T>.second() = drop(1).first()

fun <T> List<T>.plusNotNull(item: T?) =
    item?.let { this + item } ?: this
