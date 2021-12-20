package ad.kata.aoc2021.day12

fun main() {
    val caveSystem = caveSystemFromInput("day12.input")

    println("-- Day 12: Passage Pathing --")
    /* part 1 */
    val numberOfDistinctPaths = caveSystem.findPaths().size
    println("number of distinct paths through the cave system: $numberOfDistinctPaths")
    /* part 2 */
    val numberOfPathsWithMoreTime = caveSystem.findPathsWithSlightlyMoreTime().size
    println("number of distinct paths with one small cave visited twice: $numberOfPathsWithMoreTime")
}