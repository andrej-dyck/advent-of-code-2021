package ad.kata.aoc2021

fun <T> String.parseArray(transform: (String) -> T) =
    removeSurrounding("[", "]")
        .split(',')
        .map(String::trim)
        .filter(String::isNotEmpty)
        .map(transform)

fun String.parseIntArray() = parseArray { it.toInt() }