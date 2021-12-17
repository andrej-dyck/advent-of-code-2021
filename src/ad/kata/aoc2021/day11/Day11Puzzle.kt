package ad.kata.aoc2021.day11

fun main() {
    val octopuses = octopusesFromInput("day11.input")

    println("-- Dumbo Octopus --")
    /* part 1 */
    val totalFlashes = octopuses.totalFlashesAfter(steps = 100)
    println("Total flashes after 100 steps: $totalFlashes")
}