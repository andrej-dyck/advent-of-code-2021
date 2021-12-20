package ad.kata.aoc2021.day14

import ad.kata.aoc2021.extensions.splitTrim

data class InsertionRules(private val rules: Map<ElementPair, Element>) {

    constructor(rules: Sequence<InsertionRule>) : this(rules.toMap())

    fun pairInsertionOn(polymer: Polymer) =
        if (polymer.length() <= 1) polymer
        else Polymer(polymer.elements().applyRules())

    private fun Sequence<Element>.applyRules() =
        "${first()}" + windowed(2).joinToString("") { (e1, e2) ->
            applyMatchingRule(e1, e2) ?: "$e2"
        }

    private fun applyMatchingRule(e1: Element, e2: Element) =
        rules[e1 to e2]?.let { "$it${e2}" }
}

typealias InsertionRule = Pair<ElementPair, Element>

fun String.parseInsertionRule() =
    splitTrim("->").let { (pair, e) -> elementPairOf(pair) to Element(e.first()) }

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