package ad.kata.aoc2021

fun <T> String.parseList(transform: (String) -> T) =
    removeSurrounding("[", "]")
        .split(',')
        .map(String::trim)
        .filter(String::isNotEmpty)
        .map(transform)

fun String.parseIntList() = parseList { it.toInt() }

fun <T> String.parsePair(transform: (String, String) -> T) =
    removeSurrounding("(", ")")
        .split(',')
        .map(String::trim)
        .let { (i1, i2) -> transform(i1, i2) }

fun String.parseIntPair() = parsePair { i1, i2 -> i1.toInt() to i2.toInt() }