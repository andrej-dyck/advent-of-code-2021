package ad.kata.aoc2021

/* sequences and lists of form [a,b,c,d,...] */
fun <T> String.parseSequence(delimiter: Char = ',', transform: (String) -> T) =
    removeSurrounding("[", "]")
        .splitToSequence(delimiter)
        .map(String::trim)
        .filter(String::isNotEmpty)
        .map(transform)

fun <T> String.parseList(delimiter: Char = ',', transform: (String) -> T) =
    parseSequence(delimiter, transform).toList()

fun String.toIntSequence() = parseSequence { it.toInt() }

fun String.toListOfLists(outerDelimiter: Char, innerDelimiter: Char = ',') =
    parseList(outerDelimiter) { l -> l.parseList(innerDelimiter) { v -> v } }

/* vectors of form (a,b) */
private fun <T> String.parseVector(transform: (List<String>) -> T) =
    removeSurrounding("(", ")")
        .split(',')
        .map(String::trim)
        .let(transform)

fun String.toIntPair() =
    parseVector { (i1, i2) -> i1.toInt() to i2.toInt() }

fun String.toIntTriple() =
    parseVector { (i1, i2, i3) -> Triple(i1.toInt(), i2.toInt(), i3.toInt()) }