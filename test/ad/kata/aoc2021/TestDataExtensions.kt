package ad.kata.aoc2021

fun <T> String.parseList(transform: (String) -> T) =
    removeSurrounding("[", "]")
        .split(',')
        .map(String::trim)
        .filter(String::isNotEmpty)
        .map(transform)

fun String.toIntList() = parseList { it.toInt() }

private fun <T> String.parseVector(transform: (List<String>) -> T) =
    removeSurrounding("(", ")")
        .split(',')
        .map(String::trim)
        .let(transform)

fun String.toIntPair() =
    parseVector { (i1, i2) -> i1.toInt() to i2.toInt() }

fun String.toIntTriple() =
    parseVector { (i1, i2, i3) -> Triple(i1.toInt(), i2.toInt(), i3.toInt()) }