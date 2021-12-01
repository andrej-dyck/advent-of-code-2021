package ad.kata.aoc2021.extensions

/* sequence extensions */
fun <T> Sequence<T>.headTail(): Pair<T?, Sequence<T>> {
    val iterator = iterator()
    val head = if (iterator.hasNext()) iterator.next() else null

    return head to iterator.asSequence()
}

/* string sequence extensions */
fun Sequence<String>.csvLines() =
    map { it.split(',').map(String::trim) }

fun Sequence<String>.csvLinesWithHeaders() =
    csvLines().headTail().let { (headers, rows) ->
        rows.map { headers?.zip(it)?.toMap().orEmpty() }
    }
