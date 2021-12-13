package ad.kata.aoc2021.extensions

inline fun <K, V> Iterable<Pair<K, V>>.toMapMerging(merge: (List<V>) -> V) =
    groupBy { it.first }.mapValues { merge(it.value.map { p -> p.second }) }

fun <K, V> Map<K, out V?>.filterNotNullValues(): Map<K, V> =
    entries.mapNotNull { (k, v) -> v?.let { k to v } }.toMap()