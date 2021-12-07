package ad.kata.aoc2021.day07

fun main() {
    val crabPositions = crabPositionsFromInput("day07.input")

    println("-- The Treachery of Whales --")
    /* part 1 */
    val minFuel = crabPositions.alignmentCosts()
    println("fuel spend to align crabs into position: $minFuel")
}