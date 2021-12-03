package ad.kata.aoc2021.extensions

/* sequence extensions */
fun <T> Sequence<T>.headTail(): Pair<T?, Sequence<T>> {
    val iterator = iterator()
    val head = if (iterator.hasNext()) iterator.next() else null

    return head to iterator.asSequence()
}

/* string sequence extensions */
fun Sequence<String>.csvLines(delimiter: Char = ',') =
    filter(String::isNotBlank).map {
        it.split(delimiter).map(String::trim)
    }

fun Sequence<String>.csvLinesWithHeaders(delimiter: Char = ',') =
    csvLines(delimiter).headTail().let { (headers, rows) ->
        rows.map { headers?.zip(it)?.toMap().orEmpty() }
    }