package ad.kata.aoc2021.day14

fun main() {
    val polymerization = polymerizationFromInput("day14.input")

    println("-- Day 14: Extended Polymerization --")
    listOf(10, /* part 1 */ 40 /* part 2 */).forEach { steps ->
        val finalPolymer = polymerization.resultAfter(steps)
        val diffInElements = finalPolymer.differenceOfMostAndLeastCommonElement()
        println("Difference between most and least common elements (after $steps steps): $diffInElements")
    }
}