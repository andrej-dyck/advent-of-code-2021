package ad.kata.aoc2021.extensions

/* sequence extensions */
fun <T> Sequence<T>.headTail(): Pair<T?, Sequence<T>> {
    val iterator = iterator()
    val head = if (iterator.hasNext()) iterator.next() else null

    return head to iterator.asSequence()
}

fun <T> Sequence<List<T>>.transposed(): List<Sequence<T>> {
    val cols = firstOrNull()?.size ?: 0
    return (0 until cols).map { c -> map { it[c] } }
}

/* string sequence extensions */
fun Sequence<String>.csvLines(delimiter: Char = ',') =
    filter(String::isNotBlank).map { it.splitTrim(delimiter) }

fun Sequence<String>.csvLinesWithHeaders(delimiter: Char = ',') =
    csvLines(delimiter).headTail().let { (headers, rows) ->
        rows.map { headers?.zip(it)?.toMap().orEmpty() }
    }
