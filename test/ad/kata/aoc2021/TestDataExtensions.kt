package ad.kata.aoc2021

fun <T> String.parseList(transform: (String) -> T) =
    removeSurrounding("[", "]")
        .split(',')
        .map(String::trim)
        .filter(String::isNotEmpty)
        .map(transform)

fun String.parseIntList() = parseList { it.toInt() }