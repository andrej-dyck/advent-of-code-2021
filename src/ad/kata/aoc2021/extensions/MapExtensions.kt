package ad.kata.aoc2021.extensions

fun <K, V> Iterable<Pair<K, V>>.toMapMerging(merge: (List<V>) -> V) =
    groupBy { it.first }.mapValues { merge(it.value.map { p -> p.second }) }