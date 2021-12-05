package ad.kata.aoc2021

/* sequences of form a^b^c^d^... */
fun <T> String.parseSequence(delimiter: Char = '^', transform: (String) -> T) =
    splitToSequence(delimiter)
        .map(String::trim)
        .filter(String::isNotEmpty)
        .map(transform)

fun String.parseIntSequence(delimiter: Char = '^') = parseSequence(delimiter) { it.toInt() }

/* lists of form [a,b,c,d,...] */
fun <T> String.parseList(delimiter: Char = ',', transform: (String) -> T) =
    removeSurrounding("[", "]")
        .parseSequence(delimiter, transform)
        .toList()

fun <T> String.parseListAsSequence(delimiter: Char = ',', transform: (String) -> T) =
    parseList(delimiter, transform).asSequence()

fun String.parseIntList(delimiter: Char = ',') = parseList(delimiter, String::toInt)
fun String.parseStringList(delimiter: Char = ',') = parseList(delimiter, String::toString)

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