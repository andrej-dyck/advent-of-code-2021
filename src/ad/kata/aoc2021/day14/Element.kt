package ad.kata.aoc2021.day14

@JvmInline
value class Element(val value: Char) {

    override fun toString() = value.toString()
}

typealias ElementPair = Pair<Element, Element>

fun elementPairOf(elementPair: String): ElementPair {
    require(elementPair.length == 2)
    return elementPairOf(elementPair[0], elementPair[1])
}

fun elementPairOf(e1: Char, e2: Char) = Element(e1) to Element(e2)