package ad.kata.aoc2021.day14

import ad.kata.aoc2021.extensions.minMaxOrNull
import ad.kata.aoc2021.extensions.toMapMerging

data class Polymer(private val elementPairs: Map<ElementPair, Long>) {

    init {
        require(elementPairs.isNotEmpty())
    }

    constructor(template: String) : this(
        template
            .windowed(2) { Element(it[0]) to Element(it[1]) }
            .map { it to 1L }
            .toMapMerging { it.sum() }
    )

    fun flatMap(transform: (ElementPair) -> List<ElementPair>) = Polymer(
        elementPairs
            .flatMap { kv -> transform(kv.key).map { it to kv.value } }
            .toMapMerging { it.sum() }
    )

    fun length() = elementCount().values.sum()

    fun elementCount(): Map<Element, Long> =
        with(elementPairs.entries) {
            map { it.key.second to it.value } + (first().key.first to 1L)
        }.toMapMerging { it.sum() }
}

@Suppress("FunctionMaxLength")
fun Polymer.differenceOfMostAndLeastCommonElement() =
    elementCount().values.minMaxOrNull()?.let { (min, max) -> max - min } ?: 0
