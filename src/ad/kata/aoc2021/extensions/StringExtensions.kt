package ad.kata.aoc2021.extensions

/* split */
fun String.splitTrim(vararg delimiters: Char) = split(*delimiters).map(String::trim)
fun String.splitTrim(vararg delimiters: String) = split(*delimiters).map(String::trim)
fun String.splitTrim(regex: Regex) = split(regex).map(String::trim)
fun String.splitTrimToSequence(vararg delimiters: Char) =
    splitToSequence(*delimiters).map(String::trim)

/* ints */
fun String.toInts() = splitTrim(',').map(String::toInt)
fun String.toIntsSequence() = splitTrimToSequence(',').map(String::toInt)