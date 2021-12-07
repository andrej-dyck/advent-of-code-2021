package ad.kata.aoc2021.extensions

@Suppress("LongMethod", "NestedBlockDepth") // for performance reasons
fun <T: Comparable<T>> Iterable<T>.minMaxOrNull(): Pair<T,T>? {
    if (none()) return null

    val iterator = iterator()

    var minValue = iterator.next()
    var maxValue = minValue
    iterator.forEachRemaining {
        if (it < minValue) minValue = it
        else if (it > maxValue) maxValue = it
    }

    return minValue to maxValue
}