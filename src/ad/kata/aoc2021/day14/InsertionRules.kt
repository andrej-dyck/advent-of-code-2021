package ad.kata.aoc2021.day14

import ad.kata.aoc2021.extensions.splitTrim

data class InsertionRules(private val rules: Map<ElementPair, Element>) {

    constructor(rules: Sequence<InsertionRule>) : this(rules.toMap())

    fun pairInsertionOn(polymer: Polymer) = polymer.flatMap { pair ->
        rules[pair]?.let { listOf(pair.first to it, it to pair.second) } ?: listOf(pair)
    }
}

typealias InsertionRule = Pair<ElementPair, Element>

fun String.parseInsertionRule() =
    splitTrim("->").let { (pair, e) -> elementPairOf(pair) to Element(e.first()) }
