package ad.kata.aoc2021.day14

fun main() {
    val polymerization = polymerizationFromInput("day14.input")

    println("-- Day 14: Extended Polymerization --")
    /* part 1 */
    val polymerAfter10Steps = polymerization.processResults().take(10).last()
    val diffInElements = polymerAfter10Steps.differenceOfMostAndLeastCommonElement()
    println("Difference between most and least common elements (after 10 steps): $diffInElements")
}