package ad.kata.aoc2021.day07

fun main() {
    val crabPositions = crabPositionsFromInput("day07.input")

    println("-- Day 7: The Treachery of Whales --")
    /* part 2 */
    val minFuel = crabPositions.alignmentCosts()
    println("fuel spend to align crabs into position: $minFuel")
}