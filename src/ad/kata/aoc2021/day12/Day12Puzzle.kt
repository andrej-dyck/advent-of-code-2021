package ad.kata.aoc2021.day12

fun main() {
    val caveSystem = caveSystemFromInput("day12.input")

    println("-- Passage Pathing --")
    /* part 1 */
    val numberOfDistinctPaths = caveSystem.distinctPaths().size
    println("number of distinct paths through the cave system: $numberOfDistinctPaths")
}