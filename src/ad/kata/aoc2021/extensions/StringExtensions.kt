package ad.kata.aoc2021.extensions

/* split */
fun String.splitTrim(vararg delimiters: Char) = split(*delimiters).map(String::trim)
fun String.splitTrim(vararg delimiters: String) = split(*delimiters).map(String::trim)
fun String.splitTrim(regex: Regex) = split(regex).map(String::trim)