package ad.kata.aoc2021.day14

import ad.kata.aoc2021.extensions.minMaxOrNull

@JvmInline
value class Polymer(private val template: String) {

    fun elements() = template.asSequence().map { Element(it) }

    fun length() = template.length

    fun elementCount() = elements().groupingBy { it }.eachCount()
}

@Suppress("FunctionMaxLength")
fun Polymer.differenceOfMostAndLeastCommonElement() =
    elementCount().values.minMaxOrNull()?.let { (min, max) -> max - min } ?: 0