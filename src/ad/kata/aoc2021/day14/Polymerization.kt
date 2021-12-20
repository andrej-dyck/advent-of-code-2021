package ad.kata.aoc2021.day14

import ad.kata.aoc2021.PuzzleInput
import ad.kata.aoc2021.extensions.headTail
import ad.kata.aoc2021.nonEmptyLines

class Polymerization(val template: Polymer, val insertionRules: InsertionRules) {

    fun processResults() =
        generateSequence(template) { insertionRules.pairInsertionOn(it) }.drop(1)
}

fun polymerizationFromInput(filename: String) =
    PuzzleInput(filename).nonEmptyLines().headTail().let { (t, r) ->
        Polymerization(
            template = Polymer(t ?: ""),
            insertionRules = InsertionRules(r.map { it.parseInsertionRule() })
        )
    }