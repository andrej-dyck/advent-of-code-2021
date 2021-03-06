package ad.kata.aoc2021.day11

fun main() {
    val octopuses = octopusesFromInput("day11.input")

    println("-- Day 11: Dumbo Octopus --")
    /* part 1 */
    val totalFlashes = octopuses.totalFlashesAfter(steps = 100)
    println("Total flashes after 100 steps: $totalFlashes")
    /* part 2 */
    val syncFlash = octopuses.firstFlashSync()
    println("All octopuses flash simultaneously after: $syncFlash")
}